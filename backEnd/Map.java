package backEnd;

import frontEnd.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

    private int[][] bitMap;
    private Game game;
    private int size;
    private List<int[]> playerCoords;
    private int headX;
    private int headY;
    private int playerLength;
    private int foodX;
    private int foodY;
    private Direction curDir;

    public Map(Game game, int size) {
        this.game = game;
        this.size = size;
        bitMap = new int[size][size];
        curDir = Direction.UP;
        playerCoords = new ArrayList<>();
        headX = size/2;
        headY = size/2;
        playerLength = 3;
        for (int y = size/2; y < size/2 + playerLength; y++) {
            bitMap[size/2][y] = 1;
            playerCoords.add(new int[]{size/2, y});
        }
        int x = ThreadLocalRandom.current().nextInt(0, size);
        int y = ThreadLocalRandom.current().nextInt(0, size);
        while (isPlayer(x, y) || isFood(x, y)) {
            x = ThreadLocalRandom.current().nextInt(0, size);
            y = ThreadLocalRandom.current().nextInt(0, size);
        }
        bitMap[x][y] = 2;
        foodX = x;
        foodY = y;
    }

    public int getSize() {
        return bitMap.length;
    }

    public boolean isPlayer(int x, int y) {
        return x >= 0 && x < bitMap.length && y >= 0 && y < bitMap.length && bitMap[x][y] == 1;
    }

    public boolean isFood(int x, int y) {
        return x >= 0 && x < bitMap.length && y >= 0 && y < bitMap.length && bitMap[x][y] == 2;

    }

    //private boolean canMove()

    public void updateMap() {
        if (foodX > -1 && foodY > -1) {
            if (headX == foodX && headY == foodY) {
                playerLength++;
                foodX = -1;
                foodY = -1;
            }
            switch (curDir) {
                case UP:
                    headY--;
                    break;
                case DOWN:
                    headY++;
                    break;
                case LEFT:
                    headX--;
                    break;
                case RIGHT:
                    headX++;
                    break;
            }
            playerCoords.add(0, new int[]{headX, headY});
            if (headX < 0 || headX >= size || headY < 0 || headY >= size || headHasHitBody()) {
                game.gameOver();
                return;
            }
            bitMap[headX][headY] = 1;
            if (playerLength == playerCoords.size() - 1) {
                int removeIndex = playerCoords.size() - 1;
                bitMap[playerCoords.get(removeIndex)[0]][playerCoords.get(removeIndex)[1]] = 0;
                playerCoords.remove(playerCoords.size() - 1);
            }

        }
        if (foodX == -1 || foodY == -1) {
            int x = ThreadLocalRandom.current().nextInt(0, size);
            int y = ThreadLocalRandom.current().nextInt(0, size);
            while (isPlayer(x, y) || isFood(x, y)) {
                x = ThreadLocalRandom.current().nextInt(0, size);
                y = ThreadLocalRandom.current().nextInt(0, size);
            }
            foodX = x;
            foodY = y;
            bitMap[x][y] = 2;
        }
    }

    private boolean headHasHitBody() {
        for (int[] bodyCoords : playerCoords.subList(1, playerCoords.size())) {
            if (bodyCoords[0] == headX && bodyCoords[1] == headY) return true;
        }
        return false;
    }

    public Direction getCurDir() {
        return curDir;
    }

    public void setCurDir(Direction curDir) {
        this.curDir = curDir;
    }

    public int getPlayerLength() {
        return playerLength;
    }
}
