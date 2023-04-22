package com.tetris;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameForm extends JFrame {
    public static final int FRAME_WIDTH = 230;
    public static final int FRAME_HEIGHT = 420;

    public GameForm() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new instance of GameBoard
        GameBoard gameBoard = new GameBoard(10, 20, this);
        // Add the game board to the content pane of the frame
        getContentPane().add(gameBoard);

        // Set the size of the frame to be slightly larger than the preferred size of
        // the game board
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                // Check for the spacebar key code and do something withit.

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gameBoard.spawnRandomBlock(); // test
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // moves the block one step left
                    gameBoard.translateActiveCell(0, -1);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // move the block onestep right
                    gameBoard.translateActiveCell(0, 1);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) { // rotates the block
                    gameBoard.rotateActiveBoard();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // go to the next move, one step down
                    gameBoard.translateActiveCell(1, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_P) { // pause / play the game
                }
            }
        });
        Dimension boardSize = gameBoard.getPreferredSize();
        setSize(boardSize.width + 50, boardSize.height + 50);
        setResizable(false); // making the game screen non-resizable.

        // Center the frame on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 2;
        setLocation(centerX, centerY);

        setVisible(true);
    }
}
