package com.tetris.Views;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.tetris.GameForm;

public class GamePanel extends JPanel implements Runnable {

    Thread gamePanelThread;
    public boolean runn = false;
    GameBoard gameBoard = new GameBoard();
    MainMenuScreen mainMenuScreen = new MainMenuScreen();
    // private boolean showBoard = false;

    public GamePanel() {
        setBounds(GameForm.rectangle);
    }

    @Override
    public void run() {
        System.out.println("run method called");
        while (runn) {
            System.out.println("Working");
            update();
            repaint();
        }
    }

    public void update() {
        System.out.println("updating");
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("repainting");
    }

    public void startGameThread() {
        System.out.println("This method is called");
        gamePanelThread = new Thread();
        gamePanelThread.start();
        runn = true;
    }
}
