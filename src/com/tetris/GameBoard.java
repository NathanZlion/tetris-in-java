package com.tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameBoard extends JPanel {
    private int numberOfColumns;
    private int numberOfRows;
    private int gameBoardWidth = 200;
    private int gameBoardHeight = 400;
    private final int startX;
    private final int startY;
    int CellHeightSize;
    int CellWidthSize;

    // hold an Array Of Array of Cells;
    Cell[][] boardCells;

    public GameBoard(int numberOfColumns, int numberOfRows, GameForm gameForm) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;

        // Calculate the starting X and Y coordinates to center the game board
        this.startX = (GameForm.FRAME_WIDTH - gameBoardWidth) / 2;
        this.startY = (GameForm.FRAME_HEIGHT - gameBoardHeight) / 2;
        CellHeightSize = this.gameBoardHeight / this.numberOfRows;
        CellWidthSize = this.gameBoardWidth / this.numberOfColumns;

        // Set the border and background color of the game board
        this.setBorder(new LineBorder(Color.BLACK, 2));
        this.setBackground(Color.WHITE);

        boardCells = new Cell[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                boardCells[row][col] = new Cell(Color.pink, false, false);
            }
        }
    }

    /**
     * Should spawn a new random Block and shows it.
     */
    public void spawnRandomBlock() {
        System.out.println("Space bar pressed");
        for (Cell[] row : boardCells) {
            for (Cell c : row) {
                c.setIsVisible(!c.getIsVisible());
            }
        }

        // does the re-rendering the screen
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the bounds of the game board to center it
        this.setBounds(this.startX, this.startY, this.gameBoardWidth, this.gameBoardHeight);

        // Calculate the size of each Cell in the game board

        // Draw the Cells of the game board
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                g.setColor(boardCells[row][col].getIsVisible() ? boardCells[row][col].getColor() : Color.GREEN); //
                // white fill color for empty Cells

                g.fillRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize, CellHeightSize);
                g.setColor(Color.BLACK); // black borders
                g.drawRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize, CellHeightSize);
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.gameBoardWidth, this.gameBoardHeight);
    }

}
