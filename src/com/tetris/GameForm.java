package com.tetris;


import java.awt.Rectangle;

import javax.swing.JFrame;

import com.tetris.Views.GamePanel;

public class GameForm extends JFrame {

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 600;
    public static final Rectangle rectangle = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    public GameForm() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(rectangle);

        GamePanel gamepanel = new GamePanel(this);
        gamepanel.startGameThread();

        add(gamepanel);
        setLocationRelativeTo(null);
    }
}
