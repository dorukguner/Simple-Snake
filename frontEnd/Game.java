package frontEnd;

import backEnd.KeyListener;
import backEnd.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {

    private boolean paused = false;
    private boolean started = false;
    private boolean gameOver = false;
    private JPanel mainPanel;
    private Map map;
    private Timer timer;
    private int score;

    public static void main(String[] args) {
        Game game = new Game();
        game.setSize(515, 538);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }

    public Game() {
        initUI();
    }

    private void initUI() {
        mainPanel = new JPanel();
        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(e -> {
            started = true;
            mainPanel.setVisible(false);
            try {
                initGame(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(e -> {
            started = true;
            mainPanel.setVisible(false);
            try {
                initGame(75);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(e -> {
            started = true;
            mainPanel.setVisible(false);
            try {
                initGame(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(easyButton);
        mainPanel.add(mediumButton);
        mainPanel.add(hardButton);

        add(mainPanel);
        setFocusable(true);
    }

    private void initGame(int timerDelay) throws InterruptedException {
        score = 0;
        map = new Map(this, 25);
        KeyListener keyListener = new KeyListener(this, map);
        addKeyListener(keyListener);
        //setSize(500, 525);
        JPanel gamePanel = new GamePanel(this, map);
        timer = new Timer(timerDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = (map.getPlayerLength() - 3) * 100;
                keyListener.setCanMove(true);
                map.updateMap();
                repaint();
                if (gameOver) {
                    remove(gamePanel);
                    mainPanel.setVisible(true);
                    timer.stop();
                    gameOver = false;
                    started = false;
                }
            }
        });
        timer.start();
        gamePanel.setLayout(new GridBagLayout());
        add(gamePanel);

    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isStarted() {
        return started;
    }

    public void pause() {
        paused = !paused;
        if (paused) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    public int getScore() {
        return score;
    }

    public void gameOver() {
        System.out.println("Game over, your score was " + score);
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
