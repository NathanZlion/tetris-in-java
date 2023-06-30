package com.tetris.Views;

import java.awt.Color;
import java.awt.Graphics2D;

import com.tetris.GameForm;
import com.tetris.Entity.Cell;
import com.tetris.Entity.Blocks.Block;

public class GameBoard implements Runnable {

    // GAME BOARD SETTINGS
    private final int TILE_SIZE = 40;
    private static int numberRowsToSpawnNewBlock = 4;
    public int padding = 50;

    public int numberOfColumns = (GameForm.FRAME_WIDTH - (2 * padding)) / TILE_SIZE;
    public int numberOfRows = (GameForm.FRAME_HEIGHT - (2 * padding)) / TILE_SIZE + numberRowsToSpawnNewBlock;

    private Cell[][] boardCells;
    public boolean inPlay = true;
    public boolean gameOver = false;
    private int OneTilePoint = 1;
    public int score;

    Block currentActiveBlock;
    int currentActiveBlockRowPosition;
    int currentActiveBlockColumnPosition;

    Thread gameThread;

    public GameBoard() {
        boardCells = new Cell[numberOfRows][numberOfColumns];
        reset();
    }

    private void reset() {
        // RESET ALL CELLS TO INACTIVE AND INVISIBLE
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                boardCells[row][col] = new Cell();
            }
        }

        // RESET SCORE
        score = 0;
        gameOver = false;
    }

    public void draw(Graphics2D g2) {
        // draws the game board on the screen
        // Draw the spawning Area: Just for debugging purposse
        for (int row = 0; row < numberRowsToSpawnNewBlock; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                // white fill color for empty Cells
                int xPosition = col * TILE_SIZE + padding;
                int yPosition = row * TILE_SIZE + padding - TILE_SIZE * numberRowsToSpawnNewBlock;
                g2.setColor(boardCells[row][col].getIsVisible() ? boardCells[row][col].getColor().darker().darker()
                        : Color.GRAY);
                g2.fillRect(xPosition, yPosition, TILE_SIZE, TILE_SIZE);
                g2.setColor(Color.BLACK); // black borders
                g2.drawRect(xPosition, yPosition, TILE_SIZE, TILE_SIZE);
            }
        }

        // Draw the Cells of the game board
        for (int row = numberRowsToSpawnNewBlock; row < boardCells.length; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                int xPosition = col * TILE_SIZE + padding;
                int yPosition = row * TILE_SIZE + padding - TILE_SIZE * numberRowsToSpawnNewBlock;

                // white fill color for empty Cells
                g2.setColor(boardCells[row][col].getIsVisible() ? boardCells[row][col].getColor() : Color.WHITE);
                g2.fillRect(xPosition, yPosition, TILE_SIZE, TILE_SIZE);
                g2.setColor(Color.BLACK); // black borders
                g2.drawRect(xPosition, yPosition, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startGame() {
        this.inPlay = true;
    }

    public void pauseGame() {
        this.inPlay = false;
    }

    @Override
    public void run() {
        /* the game logic inside the board */
        while (gameThread != null) {
            if (inPlay & !gameOver) {
                /* if an active cell exists : */
                if (activeCellsExist()) {
                    int[] down = { 1, 0 };
                    /* if moving it down is possible : */
                    if (isPossibleTranslation(down)) {
                        makeActiveCellsInactiveAndInvisible();
                        /* move the active cell down 1 step */
                        translateActiveCell(down);
                        paintActiveCellStateOnBoard();
                    }
                    // else (if moving it down is not possible) :
                    else {
                        /* make all active cells inactive */
                        setAllActiveCellsInactive();
                    }
                }
                /* else (if there is no active cell) : */
                else {
                    /* if there is a visible block inside the first spawning area : */
                    if (VisibleExistsinSpawning()) { /* GAME OVER */
                        /* record highscore and game over (set inPlay to false) */
                        gameOver();
                    }
                    /* else (there is no visible cell) : */
                    else {
                        /* add a new block */
                        eraseCompleteLines();
                        spawnRandomBlock();
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }
    }

    private void gameOver() {
        System.out.println("Game Over");
        gameOver = true;
    }

    private Boolean isInbound(int row, int col) {
        return (0 <= row) & (row < boardCells.length) & (0 <= col) & (col < boardCells[0].length);
    }

    public void translateActiveCell(int[] direction) {
        int translationRow = direction[0];
        int translationColumn = direction[1];
        if (currentActiveBlock != null) {
            currentActiveBlockRowPosition += translationRow;
            currentActiveBlockColumnPosition += translationColumn;
        } else {
            spawnRandomBlock();
            translateActiveCell(direction);
        }
    }

    /**
     *
     * @param direction
     * @return returns `True` if all active cells when translated with given
     *         translation the
     *         current active cells are inbound and do not coincide with other
     *         visible
     *         cells. else False.
     *
     */
    private Boolean isPossibleTranslation(int[] direction) {
        int translationRow = direction[0];
        int translationColumn = direction[1];

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

    private boolean VisibleExistsinSpawning() {
        int currRow = 0;
        for (Cell cellRow[] : boardCells) {
            if (currRow >= numberRowsToSpawnNewBlock) {
                break;
            }
            for (Cell cell : cellRow) {
                if (cell.getIsVisible()) {
                    return true;
                }
            }
            currRow++;
        }
        return false;
    }

    private boolean activeCellsExist() {
        for (Cell cellRow[] : boardCells) {
            for (Cell cell : cellRow) {
                if (cell.getIsActive()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void spawnRandomBlock() {
        Block newBlock = Block.getRandomBlockType();
        addNewBlockToBoard(newBlock);
    }

    private void addNewBlockToBoard(Block newBlock) {
        currentActiveBlock = newBlock;
        Color newBlockColor = newBlock.getColor();
        int newBlockGridSize = currentActiveBlock.getGridSize();
        int[][] newBlockState = newBlock.getCurrentState();
        int shiftedRowPosition, shiftedColumnPosition;
        /* place it just above the bottom of spawning area */
        currentActiveBlockRowPosition = numberRowsToSpawnNewBlock - newBlockGridSize;
        /* blace it at the center horizontally */
        currentActiveBlockColumnPosition = (numberOfColumns - newBlockGridSize) / 2;

        for (int row = 0; row < newBlockGridSize; row++) {
            for (int col = 0; col < newBlockGridSize; col++) {
                shiftedRowPosition = currentActiveBlockRowPosition + row;
                shiftedColumnPosition = currentActiveBlockColumnPosition + col;
                if (newBlockState[row][col] == 1) {
                    boardCells[shiftedRowPosition][shiftedColumnPosition].setColor(newBlockColor);
                    boardCells[shiftedRowPosition][shiftedColumnPosition].setIsVisible(true);
                    boardCells[shiftedRowPosition][shiftedColumnPosition].setIsActive(true);
                }
            }
        }
    }

    private void eraseCompleteLines() {
        // detect a complete line and call the delete line with it's row index
        for (int row = 0; row < boardCells.length; row++) {
            if (isLineComplete(row)) {
                score = score + (OneTilePoint * numberOfColumns);
                eraseLine(row);
            }
        }
    }

    private Boolean isLineComplete(int rowIndex) {
        for (Cell cell : boardCells[rowIndex]) {
            if (!cell.getIsVisible() && !cell.getIsActive())
                return false;
        }
        return true;
    }

    private void eraseLine(int rowIndex) {
        /*
         * replace all lines with the one above the starting from the rowIndex
         * given going up. replacing both visibility and color.
         */
        for (int row = rowIndex; row > 0; row--) {
            for (int col = 0; col < boardCells[0].length; col++) {
                boardCells[row][col].setColor(boardCells[row - 1][col].getColor());
                boardCells[row][col].setIsVisible(boardCells[row - 1][col].getIsVisible());
            }
        }
    }

    private void setAllActiveCellsInactive() {
        for (Cell cellRow[] : boardCells) {
            for (Cell cell : cellRow) {
                cell.setIsActive(false);
            }
        }
    }

    private void paintActiveCellStateOnBoard() {
        int[][] newState = currentActiveBlock.getCurrentState();

        for (int row = 0; row < newState.length; row++) {
            for (int col = 0; col < newState.length; col++) {
                if (newState[row][col] == 1) {
                    int newRow = currentActiveBlockRowPosition + row;
                    int newCol = currentActiveBlockColumnPosition + col;
                    boardCells[newRow][newCol].setIsVisible(true);
                    boardCells[newRow][newCol].setIsActive(true);
                    boardCells[newRow][newCol].setColor(currentActiveBlock.getColor());
                }
            }
        }
    }

    private void makeActiveCellsInactiveAndInvisible() {
        for (Cell[] row : boardCells) {
            for (Cell c : row) {
                if (c.getIsActive()) {
                    c.setIsActive(false);
                    c.setIsVisible(false);
                }
            }
        }
    }
}
