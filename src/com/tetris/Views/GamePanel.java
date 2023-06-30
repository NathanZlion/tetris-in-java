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

    public GamePanel() {
        setBounds(GameForm.rectangle);
        gameBoard = new GameBoard();
        gameBoard.startGameThread();
    }

    @Override
    public void run() {
        while (gamePanelThread != null) {
            update();
            repaint();
        }
    }

    public void update() {
        // System.out.println("updating");
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
