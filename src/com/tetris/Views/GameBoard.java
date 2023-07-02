package com.tetris.Views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.tetris.GameForm;
import com.tetris.Entity.Cell;
import com.tetris.Entity.Directions;
import com.tetris.Entity.Blocks.Block;
import com.tetris.utils.HighScoreHandler;

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
    public int score = 0;

    // private int fastFallTime = 100;
    private int mediumFallTime = 500;
    // private int slowFallTime = 1_000;

    Block currentActiveBlock;
    int currentActiveBlockRowPosition;
    int currentActiveBlockColumnPosition;
    private BlockingQueue<int[]> inputQueue;

    Thread gameThread;
    GamePanel gamePanel;
    HighScoreHandler highScoreHandler;

    public GameBoard(GamePanel gp, HighScoreHandler highScoreHandler) {
        gamePanel = gp;
        this.highScoreHandler = highScoreHandler;
        reset();
    }

    public void reset() {
        boardCells = new Cell[numberOfRows][numberOfColumns];
        inputQueue = new LinkedBlockingQueue<>();
        // RESET ALL CELLS TO INACTIVE AND INVISIBLE
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                boardCells[row][col] = new Cell();
            }
        }

        // RESET SCORE
        score = 0;
        gameOver = false;

        // RESET BLOCKS
        currentActiveBlock = Block.getRandomBlockType();
        addNewBlockToBoard(currentActiveBlock);
    }

    public void enqueueInput(int[] direction) {
        inputQueue.offer(direction);
    }

    public void draw(Graphics2D g2) {
        paintActiveCellOnBoard();

        // paint board
        for (int row = numberRowsToSpawnNewBlock; row < numberOfRows; row++) {
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

        // paint score
        drawScore(g2);
    }

    private void drawScore(Graphics2D graphics2DImage) {
        String scoreString = Integer.toString(score);
        int stringWidthLength = (int) graphics2DImage.getFontMetrics().getStringBounds(scoreString, graphics2DImage)
                .getWidth();
        int horizontalCenter = GameForm.FRAME_WIDTH / 2 - stringWidthLength / 2;

        graphics2DImage.drawString(scoreString, horizontalCenter, 10);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startGame() {
        this.inPlay = true;
    }

    public void pauseGame() {
        highScoreHandler.writeHighScore(score);
        this.inPlay = false;
    }

    private long previousTime;
    private final long fallingTime = mediumFallTime;

    @Override
    public void run() {
        previousTime = System.currentTimeMillis();

        /* the game logic inside the board */
        while (gameThread != null) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;

            if (elapsedTime >= fallingTime) {
                previousTime = currentTime;

                if (inPlay & !gameOver) {
                    /* if an active cell exists : */
                    if (activeCellsExist()) {
                        /* if moving it down is possible : */
                        if (isPossibleTranslation(Directions.down)) {
                            makeActiveCellsInactiveAndInvisible();
                            /* move the active cell down 1 step */
                            translateActiveCell(Directions.down);
                        } else {
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
                        } else {
                            /* add a new block */
                            eraseCompleteLines();
                            spawnRandomBlock();
                        }
                    }
                }
            }

            /* don't wait for the next frame to handle input */
            while (!inputQueue.isEmpty()) {
                int[] direction = inputQueue.poll();
                if (direction != null) {
                    translateActiveCell(direction);
                }
            }
        }
    }

    private void gameOver() {
        highScoreHandler.writeHighScore(score);
        gameOver = true;
    }

    private Boolean isInbound(int row, int col) {
        return (0 <= row) & (row < boardCells.length) & (0 <= col) & (col < boardCells[0].length);
    }

    /**
     *
     * @param direction
     * @return returns `True` if all active cells when translated with given
     *         translation the
     *         current active cells are inbound and do not coincide with other
     *         visible
     *         cells. else False.
     */
    private Boolean isPossibleTranslation(int[] direction) {
        int translationRow = direction[0];
        int translationColumn = direction[1];

        for (int row = 0; row < boardCells.length; row++) {
            for (int col = 0; col < boardCells[0].length; col++) {
                if (boardCells[row][col].getIsActive()) {
                    /* If it leaves the box when translated */
                    if (!isInbound(row + translationRow, col + translationColumn))
                        return false;
                    /* If there is an adjecent active cell in the direction it is moving */
                    if (boardCells[row + translationRow][col + translationColumn].getIsActive())
                        continue;
                    /*
                     * if it hits a visible but not active cell when moved in that direction
                     * This adjecent cell is possibly a previous cell that hit the floor, So
                     * you can't move in that direction.
                     */
                    if (boardCells[row + translationRow][col + translationColumn].getIsVisible())
                        return false;
                }
            }
        }
        return true;
    }

    public void translateActiveCell(int[] direction) {
        if (!isPossibleTranslation(direction))
            return;
        int translationRow = direction[0];
        int translationColumn = direction[1];
        if (currentActiveBlock == null) {
            spawnRandomBlock();
        }
        currentActiveBlockRowPosition += translationRow;
        currentActiveBlockColumnPosition += translationColumn;
        paintActiveCellOnBoard();
    }

    public void translateActiveCellToFloor() {
        // while (isPossibleTranslation(Directions.down)) {
        // translateActiveCell(Directions.down);
        // }
        for (int _i = 0; _i < numberOfRows; _i++) {
            enqueueInput(Directions.down);
        }
    }

    private void toggleActiveCellsInactiveAndInvisible() {
        for (Cell[] row : boardCells) {
            for (Cell c : row) {
                if (c.getIsActive()) {
                    c.setIsActive(false);
                    c.setIsVisible(false);
                }
            }
        }
    }

    public void rotateActiveCell() {
        int[][] nextRotationState = currentActiveBlock.getNextRotationState();

        if (isPossibleActiveBlockShape(nextRotationState)) {
            currentActiveBlock.rotate();
            paintActiveCellOnBoard();
        }
    }

    private void paintActiveCellOnBoard() {
        toggleActiveCellsInactiveAndInvisible();
        int[][] newState = currentActiveBlock.getCurrentState();

        if (isPossibleActiveBlockShape(newState)) {
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
    }

    private Boolean isPossibleActiveBlockShape(int[][] newState) {
        int newRow, newCol;
        for (int row = 0; row < newState.length; row++) {
            for (int col = 0; col < newState.length; col++) {
                // if 1: not inbound or overlaps with other inactive visible cells
                if (newState[row][col] == 1) {
                    newRow = currentActiveBlockRowPosition + row;
                    newCol = currentActiveBlockColumnPosition + col;
                    if (!isInbound(newRow, newCol))
                        return false;
                    if (boardCells[newRow][newCol].getIsActive())
                        continue;
                    if (boardCells[newRow][newCol].getIsVisible())
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

    /**
     * Replace all lines with the one above, the starting from the rowIndex
     * given going up. replacing both visibility and color.
     */
    private void eraseLine(int rowIndex) {
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
