package com.tetris.Views;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

public class SplashScreen extends JPanel {
    private final String GAME_NAME = "Tetris";
    private Image backgroundImage;

    public SplashScreen() {
        setSize(400, 300);
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Draw the game name on top of the background image
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(GAME_NAME)) / 2;
        int y = 100;
        g.drawString(GAME_NAME, x, y);
    }
}
