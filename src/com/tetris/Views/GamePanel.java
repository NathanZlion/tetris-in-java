package com.tetris.Views;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tetris.Entity.Directions;
import com.tetris.GameForm;
import com.tetris.Controllers.keyHandlerManager;

public class GamePanel extends JPanel implements Runnable {

    Thread gamePanelThread;
    GameBoard gameBoard;

    MainMenuScreen mainMenuScreen = new MainMenuScreen();
    public boolean showGame = false; /* on start show the menu screen. */
    private final int FPS = 60;
    keyHandlerManager keyHandler = new keyHandlerManager();
    private JFrame gameFrame;

    public GamePanel(JFrame gf) {
        gameFrame = gf;
        setBounds(GameForm.rectangle);
        setDoubleBuffered(true);
        gameBoard = new GameBoard(this);
        gameBoard.startGameThread();

        addKeyListener(keyHandler);
        /* SETTING FOCUSSABLE ENABLES FOR KEY LISTENING. */
        setFocusable(true);

    }

    @Override
    public void run() {
        double drawInterval = (1_000_000_000 / FPS);
        double delta = 0; // the time change
        long previousTime = System.nanoTime();
        long currentTime;

        // Used the delta/accumulator method to limit frame refresh rate to fps
        while (gamePanelThread.isAlive()) {
            currentTime = System.nanoTime();

            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;

            // if the time change has reached or exceeded the fps
            if (delta >= 0.2) {
                update();
                repaint();
                delta = delta - 0.2;
            }
        }
    }

    public void update() {
        // Handle User Inputs here
        // if in the showGame state:
        int[] direction = null;
        if (showGame) {
            if (gameBoard.gameOver){
                showGame = false;
            }
            // check for pause and enter buttons.
            if (keyHandler.enterPressed) {
                gameBoard.translateActiveCellToFloor();
            }
            if (keyHandler.pButtonPressed) {
                /* pause the game and show menu */
                showGame = false;
                gameBoard.pauseGame();
            } else {
                if (keyHandler.leftPressed) {
                    direction = Directions.left;
                }
                if (keyHandler.rightPressed) {
                    direction = Directions.right;
                }
                if (keyHandler.downPressed) {
                    direction = Directions.down;
                }
                if (keyHandler.spacebarPressed) {
                    gameBoard.rotateActiveCell();
                }
            }
            if (direction != null) {
                gameBoard.enqueueInput(direction);
            }
        }
        /* if in menu screen */
        else {
            if (keyHandler.enterPressed) {
                switch (mainMenuScreen.selectedOption) {
                    /* New Game Selected */
                    case 0:
                        gameBoard.reset();
                        showGame = true;
                        gameBoard.startGame();
                        break;
                    /* Quit selected */
                    case 1:
                        gameFrame.dispose();
                        System.exit(0); /* Stop the program */
                        break;
                    default:
                        break;
                }
            } else {
                if (keyHandler.upPressed) {
                    mainMenuScreen.prevOption();
                }
                if (keyHandler.downPressed) {
                    mainMenuScreen.nextOption();
                }
                if (keyHandler.escapePressed) {
                    showGame = true;
                    gameBoard.startGame();
                }
            }
        }
        keyHandler.reset();
        // check for directions
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        /* clear the screen */
        g2.clearRect(0, 0, getWidth(), getHeight());

        if (showGame) {
            gameBoard.draw(g2);
        } else {
            mainMenuScreen.draw(g2);
        }
        /* for better performance */
        g2.dispose();
    }

    public void startGameThread() {
        gamePanelThread = new Thread(this);
        gamePanelThread.start();
    }
}
