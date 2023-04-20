package com.tetris;


import java.awt.Color;

import javax.swing.JFrame;

public class GameForm extends JFrame {
    public GameForm() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.red);
        setBounds(0, 0, 1000, 600);
        add(new GameBoard(10, 20, this));
    }
}
