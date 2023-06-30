package com.tetris.Views;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.tetris.GameForm;

public class GamePanel extends JPanel implements Runnable {

    Thread gamePanelThread;
    GameBoard gameBoard;

    MainMenuScreen mainMenuScreen = new MainMenuScreen();
    private boolean showGame = true;
    private final int FPS = 60;

    public GamePanel() {
        setBounds(GameForm.rectangle);
        gameBoard = new GameBoard();
        gameBoard.startGameThread();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0; // the time change
        long previousTime = System.nanoTime();
        long currentTime;

        // Used the delta/accumulator method to limit frame refresh rate to fps
        while (gamePanelThread.isAlive()) {
            currentTime = System.nanoTime();

            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;

            // if the time change has reached or exceeded the fps
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // Handle User Inputs here
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (showGame) {
            gameBoard.draw(g2);
        } else {
            mainMenuScreen.draw(g2);
        }
    }

    public void startGameThread() {
        gamePanelThread = new Thread(this);
        gamePanelThread.start();
    }
}
