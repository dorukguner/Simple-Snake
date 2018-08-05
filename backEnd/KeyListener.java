package backEnd;

import frontEnd.Game;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {


    private Game game;
    private Map map;
    private boolean canMove = true;

    public KeyListener(Game game, Map map) {
        this.game = game;
        this.map = map;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (!game.isStarted()) return;

        if (!canMove) {

            return;
        }

        int keycode = e.getKeyCode();

        if (keycode == 'p' || keycode == 'P') {
            game.pause();
            return;
        }

        if (game.isPaused()) return;

        switch (keycode) {
            case KeyEvent.VK_LEFT:
                if (map.getCurDir() != Direction.RIGHT) {
                    map.setCurDir(Direction.LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (map.getCurDir() != Direction.LEFT) {
                    map.setCurDir(Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (map.getCurDir() != Direction.UP) {
                    map.setCurDir(Direction.DOWN);
                }
                break;
            case KeyEvent.VK_UP:
                if (map.getCurDir() != Direction.DOWN) {
                    map.setCurDir(Direction.UP);
                }
                break;
        }
        canMove = false;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
