package com.tetris;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameBoard extends JPanel {
    private int numberOfColumns;
    private int numberOfRows;
    private int startX;
    private int startY;
    private int gameBoardWidth = 300;
    private int gameBoardHeight = 500;

    public GameBoard(int numberOfColumns, int numberOfRows, GameForm gameFrame) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;

        Dimension parentSize = gameFrame.getSize();
        this.startX = ((int) parentSize.getWidth() - gameBoardWidth) / 2;
        this.startY = ((int) parentSize.getHeight() - gameBoardHeight) / 2;
        
        this.setBorder(new LineBorder(Color.BLACK, 5));
        this.setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBounds(this.startX, this.startY, this.gameBoardWidth, this.gameBoardHeight);

        int cellHeightSize = this.gameBoardHeight / this.numberOfRows;
        int cellWidthSize = this.gameBoardWidth / this.numberOfColumns;

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                int x = col * cellWidthSize;
                int y = row * cellHeightSize;
                g.setColor(Color.RED);
                g.fillRect(x, y, cellWidthSize, cellHeightSize);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellWidthSize, cellHeightSize);
            }
        }
    }
}
