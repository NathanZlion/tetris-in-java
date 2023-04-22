package com.tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.tetris.Blocks.Block;

public class GameBoard extends JPanel {
    private int numberOfColumns;
    private int numberOfRows;
    private int gameBoardWidth = 200;
    private int gameBoardHeight = 400;
    private final int startX;
    private final int startY;
    static int numberRowsToSpawnNewBlock = 4;
    int CellHeightSize;
    int CellWidthSize;
    Cell[][] boardCells;
    Block currentActiveBlock;
    int currentActiveBlockRowPosition;
    int currentActiveBlockColumnPosition;

    public GameBoard(int numberOfColumns, int numberOfRows, GameForm gameForm) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows + numberRowsToSpawnNewBlock;

        // Calculate the starting X and Y coordinates to center the game board
        this.startX = (GameForm.FRAME_WIDTH - gameBoardWidth) / 2;
        this.startY = (GameForm.FRAME_HEIGHT - gameBoardHeight) / 2;
        CellHeightSize = this.gameBoardHeight / this.numberOfRows;
        CellWidthSize = this.gameBoardWidth / this.numberOfColumns;

        // Set the border and background color of the game board
        this.setBorder(new LineBorder(Color.BLACK, 5));
        this.setBackground(Color.WHITE);

        // I should leave 4 Rows on top as a spawning area for the new block.
        // This way the blocks look like they are being created outside the map
        // But the 4 rows should stay hidden.
        boardCells = new Cell[numberOfRows + numberRowsToSpawnNewBlock][numberOfColumns];
        for (int row = 0; row < boardCells.length; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                boardCells[row][col] = new Cell(Color.BLUE, false, false);
            }
        }
    }

    /**
     * Should spawn a new random Block and shows it.
     */
    public void spawnRandomBlock() {
        toggleOffActiveCells();
        Block newBlock = Block.getRandomBlockType();
        System.out.println(newBlock.getClass()); // for debugging purpose only

        addNewBlockToBoard(newBlock);
        // does the re-rendering on the screen
        repaint();
    }

    private void addNewBlockToBoard(Block newBlock) {
        currentActiveBlock = newBlock;
        Color newBlockColor = newBlock.getColor();
        int newBlockGridSize = currentActiveBlock.getGridSize();
        int[][] newBlockState = newBlock.getCurrentState();
        int shiftedRowPosition, shiftedColumnPosition;
        currentActiveBlockRowPosition = numberRowsToSpawnNewBlock - newBlockGridSize;
        currentActiveBlockColumnPosition = (numberOfColumns - newBlockGridSize) / 2;

        for (int row = 0; row < newBlockGridSize; row++) {
            for (int col = 0; col < newBlockGridSize; col++) {
                shiftedRowPosition = currentActiveBlockRowPosition + row;
                shiftedColumnPosition = currentActiveBlockColumnPosition + col;
                if (newBlockState[row][col] == 1) {
                    boardCells[shiftedRowPosition][shiftedColumnPosition].setColor(newBlockColor);
                    boardCells[shiftedRowPosition][shiftedColumnPosition].setIsVisible(true); // make visible
                    boardCells[shiftedRowPosition][shiftedColumnPosition].setIsActive(true); // make visible
                }
            }
        }
    }

    // public Boolean translateActiveCell(int translationRow, int translationColumn)
    // {
    // if (currentActiveBlock == null)
    // return false;

    // if (isPossibleTranslation(translationRow, translationColumn)) {
    // System.out.println("Fin");

    // // hold an array of all active cells translation.
    // // erase active cells that are on the board
    // // use the array you hold to toggle them on and active
    // ArrayList<int[]> translatedActiveCell = new ArrayList<>();

    // for (int row = 0; row < boardCells.length; row++) {
    // for (int col = 0; col < boardCells[0].length; col++) {
    // Cell currCell = boardCells[row][col];
    // if (currCell.getIsActive() && currCell.getIsVisible())
    // translatedActiveCell.add(new int[] { row + translationRow, col +
    // translationColumn });
    // }
    // }
    // inactiveAndInvisibleAllActiveCells();
    // for (int[] nextRowAndCol : translatedActiveCell) {
    // int row = nextRowAndCol[0];
    // int col = nextRowAndCol[1];
    // toggleCellOn(row, col, currentActiveBlock.getColor(), true);
    // }
    // repaint();
    // return true;
    // }

    // return false;
    // }
    public Boolean translateActiveCell(int translationRow, int translationColumn) {
        if (currentActiveBlock == null)
            return false;
        if (isPossibleTranslation(translationRow, translationColumn)) {
            currentActiveBlockRowPosition += translationRow;
            currentActiveBlockColumnPosition += translationColumn;
            paintActiveCellStateOnBoard();
            return true;
        }
        return false;

    }

    private void toggleCellOn(int row, int col, Color color, Boolean isActive) {
        boardCells[row][col].setColor(color);
        boardCells[row][col].setIsActive(isActive);
        boardCells[row][col].setIsVisible(true);
    }

    private void inactiveAndInvisibleAllActiveCells() {
        for (int row = 0; row < boardCells.length; row++) {
            for (int col = 0; col < boardCells[0].length; col++) {
                if (boardCells[row][col].getIsActive())
                    toggleCellInactiveAndInvisible(row, col);
            }
        }

    }

    private void inactiveAllActiveCells() {
        for (int row = 0; row < boardCells.length; row++) {
            for (int col = 0; col < boardCells[0].length; col++) {
                if (boardCells[row][col].getIsActive())
                    toggleCellInactive(row, col);
            }
        }
    }

    private Cell toggleCellInactive(int row, int col) {
        boardCells[row][col].setIsActive(false);
        return boardCells[row][col];
    }

    private void toggleCellInactiveAndInvisible(int row, int col) {
        toggleCellInactive(row, col);
        boardCells[row][col].setIsVisible(false);
    }

    public void rotateActiveBoard() {
        int[][] nextRotationState = currentActiveBlock.getNextRotationState();
        System.out.println(isPossibleActiveBlockShape(nextRotationState));
        if (isPossibleActiveBlockShape(nextRotationState)) {
            currentActiveBlock.rotate();
            paintActiveCellStateOnBoard();
        }
    }

    private void paintActiveCellStateOnBoard() {
        toggleOffActiveAndVanishCells();
        int[][] newState = currentActiveBlock.getCurrentState();

        int newRow, newCol;
        for (int row = 0; row < newState.length; row++) {
            for (int col = 0; col < newState.length; col++) {
                if (newState[row][col] == 1) {
                    newRow = currentActiveBlockRowPosition + row;
                    newCol = currentActiveBlockColumnPosition + col;
                    boardCells[newRow][newCol].setIsVisible(true);
                    boardCells[newRow][newCol].setIsActive(true);
                    boardCells[newRow][newCol].setColor(currentActiveBlock.getColor());
                }
            }
        }

        repaint();
    }

    private void toggleOffActiveAndVanishCells() {
        for (Cell[] row : boardCells) {
            for (Cell c : row) {
                if (c.getIsActive()) {
                    c.setIsActive(false);
                    c.setIsVisible(false);
                }
            }
        }
    }

    private void toggleOffActiveCells() {
        for (Cell[] row : boardCells) {
            for (Cell c : row) {
                if (c.getIsActive()) {
                    c.setIsActive(false);
                }
            }
        }
    }

    private Boolean isPossibleTranslation(int translationRow, int translationColumn) {
        // check if all active cells when translated with given translation
        // the current active cells are inbound and do not coincide with other visible
        // cells.
        for (int row = 0; row < boardCells.length; row++) {
            for (int col = 0; col < boardCells[0].length; col++) {
                if (boardCells[row][col].getIsActive()) {
                    if (!isInbound(row + translationRow, col + translationColumn))
                        return false;
                    if (boardCells[row + translationRow][col + translationColumn].getIsActive())
                        continue;
                    if (boardCells[row + translationRow][col + translationColumn].getIsVisible())
                        return false;
                }
            }
        }
        return true;
    }

    private Boolean isPossibleActiveBlockShape(int[][] newState) {
        int newRow, newCol;
        for (int row = 0; row < newState.length; row++) {
            for (int col = 0; col < newState.length; col++) {
                // if 1: not inbound or overlaps with other inactive visible cells
                if (newState[row][col] == 1) {
                    newRow = currentActiveBlockRowPosition + row;
                    newCol = currentActiveBlockColumnPosition + col;
                    if (boardCells[newRow][newCol].getIsActive())
                        continue;
                    if (!isInbound(newRow, newCol))
                        return false;
                    if (boardCells[newRow][newCol].getIsVisible())
                        return false;

                }
            }
        }
        return true;
    }

    private Boolean isInbound(int row, int col) {
        return (0 <= row) & (row < boardCells.length) & (0 <= col) & (col < boardCells[0].length);
    }

    private void eraseCompleteLines() {
    }

    private void eraseLine(int rowIndex) {
        // replace all lines with the one above the
        // starting from the rowIndex given going up.
    }

    private Boolean gameOver() {
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the bounds of the game board to center it
        this.setBounds(this.startX, this.startY, this.gameBoardWidth, this.gameBoardHeight);

        // Draw the spawning Area:
        for (int row = 0; row < numberRowsToSpawnNewBlock; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                // white fill color for empty Cells
                g.setColor(boardCells[row][col].getIsVisible() ? boardCells[row][col].getColor().darker().darker()
                        : Color.GRAY);
                g.fillRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize, CellHeightSize);
                g.setColor(Color.BLACK); // black borders
                g.drawRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize, CellHeightSize);
            }
        }

        // Draw the Cells of the game board
        for (int row = numberRowsToSpawnNewBlock; row < boardCells.length; row++) {
            for (int col = 0; col < this.numberOfColumns; col++) {
                // white fill color for empty Cells
                g.setColor(boardCells[row][col].getIsVisible() ? boardCells[row][col].getColor() : Color.WHITE);
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

    // Hold a state of the starting position.
    //
    //
    //
    //
    //
    //
    //
    //

    /*
     * hold a position array of the currently spawned block
     * chech everytime if the next move is possible.
     * - change state to next state: fall one step.
     * If not possible
     * - check if gameover:
     * - If Gameover Set the Highscore and
     * - show the Game Over View and Give option to restart or exit.
     * - else:
     * - change state to next state: fall one step.
     * 
     */

}
