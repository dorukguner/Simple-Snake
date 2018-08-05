package frontEnd;

import backEnd.Map;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Game game;
    private final Map map;
    private final int width = 500;
    private final int height = 500;
    private final int gridWidth;
    private final int gridHeight;

    public GamePanel(Game game, Map map) {
        this.game = game;
        this.map = map;
        setPreferredSize(new Dimension(width,height));
        gridWidth = width / map.getSize();
        gridHeight = height / map.getSize();
    }

    public void paintComponent (Graphics g) {

        super.paintComponent(g);

        for (int y = 0; y * gridHeight < height; y++) {
            for (int x = 0; x * gridWidth < width; x++) {
                g.setColor(Color.BLACK);
                g.fillRect(x * gridWidth,y * gridHeight,gridWidth,gridHeight);
                if (map.isPlayer(x, y)) {
                    g.setColor(Color.GREEN);
                    g.fillOval(x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                } else if (map.isFood(x, y)) {
                    g.setColor(Color.WHITE);
                    g.fillOval(x * gridWidth, y * gridHeight, gridWidth, gridHeight);

                }
            }
        }
        g.setColor(Color.GREEN);
        g.drawString("Score: " + game.getScore(), 25, 200);
        if (game.isPaused()) {
            g.drawString("Paused", 25, 300);
        }

    }

}
