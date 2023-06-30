package com.tetris;


import java.awt.Rectangle;

import javax.swing.JFrame;

import com.tetris.Views.GamePanel;

public class GameForm extends JFrame {

    public static final int FRAME_WIDTH = 230;
    public static final int FRAME_HEIGHT = 420;
    public static Rectangle rectangle = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    GameForm() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel gamepanel = new GamePanel();
        gamepanel.startGameThread();
        setBounds(rectangle);
        add(gamepanel);
    }
}

// package com.tetris;

// import java.awt.Dimension;
// import java.awt.Toolkit;
// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;
// import javax.swing.JFrame;
// import com.tetris.Views.GameBoard;
// // import com.tetris.Views.SplashScreen;

// public class GameForm extends JFrame implements Runnable {
// public static final int FRAME_WIDTH = 230;
// public static final int FRAME_HEIGHT = 420;
// // private int highscore;
// // hold state of game
// // Instance of screen => Game screen, Menu screen, High Score screen
// // Call the key handler on the current screen instance.

// public GameForm() {
// setTitle("Tetris Game");
// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// // Create a new instance of GameBoard
// GameBoard gameBoard = new GameBoard(10, 20, this);
// // Add the game board to the content pane of the frame
// getContentPane().add(gameBoard);
// // getContentPane().add(new SplashScreen());
// // Set the size of the frame to be slightly larger than the preferred size of
// // the game board

// //as a suggestion abstract this method to have it's own method or even better
// make it it's own class
// //the constructor serves as a place to set up the things you want to do in
// your class don't add logic to it.

// //so at the very least make it it's own method
// //plus I think the logic should be moved to the board instead of the Form
// //after all the board is the one painting the stuff and tracking the blocks

// addKeyListener(new KeyAdapter() {
// @Override
// public void keyPressed(KeyEvent e) {
// // Check for the spacebar key code and do something withit.
// if (e.getKeyCode() == KeyEvent.VK_ENTER) {
// gameBoard.spawnRandomBlock(); // test
// } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // moves the block one step
// left
// gameBoard.translateActiveCell(0, -1);
// } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // move the block onestep
// right
// gameBoard.translateActiveCell(0, 1);
// } else if (e.getKeyCode() == KeyEvent.VK_UP) { // rotates the block
// gameBoard.rotateActiveBoard();
// } else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // go to the next move, one
// step down
// gameBoard.translateActiveCell(1, 0);
// } else if (e.getKeyCode() == KeyEvent.VK_P) { // pause / play the game
// } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
// gameBoard.translateActiveCellAllTheWayDown();
// }
// }
// });

// Dimension boardSize = gameBoard.getPreferredSize();
// setSize(boardSize.width + 50, boardSize.height + 50);
// setResizable(false); // making the game screen non-resizable to avoid
// centering issues.

// // Center the frame on screen
// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
// int centerX = (screenSize.width - getWidth()) / 2;
// int centerY = (screenSize.height - getHeight()) / 2;
// setLocation(centerX, centerY);
// setVisible(true);
// startTetrisGame();
// }

// @Override
// public void run() {
// int frameDrawRate = 1000/60;
// // Normally this is where you would call your update and repaint functions
// //Frame draws is used for how many times a frame is drawn per second so every
// 16.6 MicroSecond repaint and update is applied

// //I just added a print to show you that the thread works
// //Try and use the paintComponent method to draw your blocks it's more
// manageable
// //java has the support that after you write the paintComponent you can call
// it here as repaint() so check that out

// while (true) {
// System.out.println("WORKS");
// try {
// Thread.sleep(frameDrawRate);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }

// public void startTetrisGame() {
// //this is called in the constructor of GameForm
// //threads have an override method called run so when .start() is called I'm
// calling the run() method
// Thread tetrisThread = new Thread(this);
// tetrisThread.start();

// }

// public static void DisplayGameOverView() {
// // to be implemented
// }

// }
