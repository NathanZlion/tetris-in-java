package com.tetris.Views;

import java.awt.Graphics2D;

public class GameBoard {

    public int numberOfColumns;
    public int numberOfRows;

    public GameBoard() {

    }

    public void draw (Graphics2D g2){
        // draws the game board on the screen
    }
}


// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Graphics;
// import javax.swing.JPanel;
// import javax.swing.border.LineBorder;
// import com.tetris.Cell;
// import com.tetris.Blocks.Block;

// private int numberOfColumns;
// private int numberOfRows;
// private int gameBoardWidth = 200;
// private int gameBoardHeight = 400;
// private final int startX;
// private final int startY;
// static int numberRowsToSpawnNewBlock = 4;
// int CellHeightSize;
// int CellWidthSize;
// Cell[][] boardCells;
// Block currentActiveBlock;
// int currentActiveBlockRowPosition;
// int currentActiveBlockColumnPosition;
// public int Highscore;

// public GameBoard(int numberOfColumns, int numberOfRows, GameForm gameForm) {
// this.numberOfColumns = numberOfColumns;
// this.numberOfRows = numberOfRows + numberRowsToSpawnNewBlock;

// // Calculate the starting X and Y coordinates to center the game board
// this.startX = (GameForm.FRAME_WIDTH - gameBoardWidth) / 2;
// this.startY = (GameForm.FRAME_HEIGHT - gameBoardHeight) / 2;
// CellHeightSize = this.gameBoardHeight / this.numberOfRows;
// CellWidthSize = this.gameBoardWidth / this.numberOfColumns;

// // Set the border and background color of the game board
// this.setBorder(new LineBorder(Color.BLACK, 5));
// this.setBackground(Color.WHITE);

// // I should leave 4 Rows on top as a spawning area for the new block.
// // This way the blocks look like they are being created outside the map
// // But the 4 rows should stay hidden.
// boardCells = new Cell[numberOfRows +
// numberRowsToSpawnNewBlock][numberOfColumns];
// for (int row = 0; row < boardCells.length; row++) {
// for (int col = 0; col < this.numberOfColumns; col++) {
// boardCells[row][col] = new Cell(Color.BLUE, false, false);
// }
// }

// }

// /**
// * Should spawn a new random Block and shows it.
// */
// public void spawnRandomBlock() {
// toggleOffActiveCells();
// eraseCompleteLines();
// Block newBlock = Block.getRandomBlockType();

// if (getGameIsOver()) {
// GameForm.DisplayGameOverView();
// }
// addNewBlockToBoard(newBlock);
// // does the re-rendering on the screen
// repaint();
// }

// private void addNewBlockToBoard(Block newBlock) {
// currentActiveBlock = newBlock;
// Color newBlockColor = newBlock.getColor();
// int newBlockGridSize = currentActiveBlock.getGridSize();
// int[][] newBlockState = newBlock.getCurrentState();
// int shiftedRowPosition, shiftedColumnPosition;
// currentActiveBlockRowPosition = numberRowsToSpawnNewBlock - newBlockGridSize;
// currentActiveBlockColumnPosition = (numberOfColumns - newBlockGridSize) / 2;

// for (int row = 0; row < newBlockGridSize; row++) {
// for (int col = 0; col < newBlockGridSize; col++) {
// shiftedRowPosition = currentActiveBlockRowPosition + row;
// shiftedColumnPosition = currentActiveBlockColumnPosition + col;
// if (newBlockState[row][col] == 1) {
// boardCells[shiftedRowPosition][shiftedColumnPosition].setColor(newBlockColor);
// boardCells[shiftedRowPosition][shiftedColumnPosition].setIsVisible(true); //
// make visible
// boardCells[shiftedRowPosition][shiftedColumnPosition].setIsActive(true); //
// make visible
// }
// }
// }
// }

// public Boolean translateActiveCell(int translationRow, int translationColumn)
// {
// if (currentActiveBlock == null)
// return false;
// if (isPossibleTranslation(translationRow, translationColumn)) {
// currentActiveBlockRowPosition += translationRow;
// currentActiveBlockColumnPosition += translationColumn;
// paintActiveCellStateOnBoard();
// return true;
// }
// return false;
// }

// public void rotateActiveBoard() {
// int[][] nextRotationState = currentActiveBlock.getNextRotationState();

// if (isPossibleActiveBlockShape(nextRotationState)) {
// currentActiveBlock.rotate();
// paintActiveCellStateOnBoard();
// }
// }

// private void paintActiveCellStateOnBoard() {
// toggleActiveCellsInactiveAndInvisible();
// int[][] newState = currentActiveBlock.getCurrentState();

// int newRow, newCol;
// for (int row = 0; row < newState.length; row++) {
// for (int col = 0; col < newState.length; col++) {
// if (newState[row][col] == 1) {
// newRow = currentActiveBlockRowPosition + row;
// newCol = currentActiveBlockColumnPosition + col;
// boardCells[newRow][newCol].setIsVisible(true);
// boardCells[newRow][newCol].setIsActive(true);
// boardCells[newRow][newCol].setColor(currentActiveBlock.getColor());
// }
// }
// }

// repaint();
// }

// private void toggleActiveCellsInactiveAndInvisible() {
// for (Cell[] row : boardCells) {
// for (Cell c : row) {
// if (c.getIsActive()) {
// c.setIsActive(false);
// c.setIsVisible(false);
// }
// }
// }
// }

// private void toggleOffActiveCells() {
// for (Cell[] row : boardCells) {
// for (Cell c : row) {
// c.setIsActive(false);
// }
// }
// }

// /**
// *
// * @param translationRow
// * @param translationColumn
// * @return returns `True` if all active cells when translated with given
// translation the
// * current active cells are inbound and do not coincide with other visible
// cells. else False.
// *
// */
// private Boolean isPossibleTranslation(int translationRow, int
// translationColumn) {

// for (int row = 0; row < boardCells.length; row++) {
// for (int col = 0; col < boardCells[0].length; col++) {
// if (boardCells[row][col].getIsActive()) {
// if (!isInbound(row + translationRow, col + translationColumn))
// return false;
// if (boardCells[row + translationRow][col + translationColumn].getIsActive())
// continue;
// if (boardCells[row + translationRow][col + translationColumn].getIsVisible())
// return false;
// }
// }
// }
// return true;
// }

// private Boolean isPossibleActiveBlockShape(int[][] newState) {
// int newRow, newCol;
// for (int row = 0; row < newState.length; row++) {
// for (int col = 0; col < newState.length; col++) {
// // if 1: not inbound or overlaps with other inactive visible cells
// if (newState[row][col] == 1) {
// newRow = currentActiveBlockRowPosition + row;
// newCol = currentActiveBlockColumnPosition + col;
// if (!isInbound(newRow, newCol))
// return false;
// if (boardCells[newRow][newCol].getIsActive())
// continue;
// if (boardCells[newRow][newCol].getIsVisible())
// return false;
// }
// }
// }
// return true;
// }

// private Boolean isInbound(int row, int col) {
// return (0 <= row) & (row < boardCells.length) & (0 <= col) & (col <
// boardCells[0].length);
// }

// private void eraseCompleteLines() {
// // detect a complete line and call the delete line with it's row index
// for (int row = 0; row < boardCells.length; row++)
// if (isLineComplete(row))
// eraseLine(row);
// }

// private void eraseLine(int rowIndex) {
// // replace all lines with the one above the
// // starting from the rowIndex given going up.

// // replacing both visibility and color.
// for (int row = rowIndex; row > 0; row--) {
// for (int col = 0; col < boardCells[0].length; col++) {
// boardCells[row][col].setColor(boardCells[row - 1][col].getColor());
// boardCells[row][col].setIsVisible(boardCells[row - 1][col].getIsVisible());
// }
// }

// // erase the 0 index row
// for (Cell cell : boardCells[0]) {
// cell.setIsVisible(false);
// }

// }

// private Boolean isLineComplete(int rowIndex) {
// for (Cell cell : boardCells[rowIndex]) {
// if (!cell.getIsVisible() && !cell.getIsActive())
// return false;
// }
// return true;
// }

// private Boolean getGameIsOver() {
// // check whether there are any visible cells on the top 4 Rows.
// for (int row = 0; row < 4; row++) {
// for (Cell cell : boardCells[row]) {
// if (cell.getIsVisible()) {
// return true;
// }
// }
// }
// return false;
// }

// @Override
// protected void paintComponent(Graphics g) {
// super.paintComponent(g);

// // Set the bounds of the game board to center it
// this.setBounds(this.startX, this.startY, this.gameBoardWidth,
// this.gameBoardHeight);

// // Draw the spawning Area:
// for (int row = 0; row < numberRowsToSpawnNewBlock; row++) {
// for (int col = 0; col < this.numberOfColumns; col++) {
// // white fill color for empty Cells
// g.setColor(boardCells[row][col].getIsVisible() ?
// boardCells[row][col].getColor().darker().darker()
// : Color.GRAY);
// g.fillRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize,
// CellHeightSize);
// g.setColor(Color.BLACK); // black borders
// g.drawRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize,
// CellHeightSize);
// }
// }

// // Draw the Cells of the game board
// for (int row = numberRowsToSpawnNewBlock; row < boardCells.length; row++) {
// for (int col = 0; col < this.numberOfColumns; col++) {
// // white fill color for empty Cells
// g.setColor(boardCells[row][col].getIsVisible() ?
// boardCells[row][col].getColor() : Color.WHITE);
// g.fillRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize,
// CellHeightSize);
// g.setColor(Color.BLACK); // black borders
// g.drawRect(col * CellWidthSize, row * CellHeightSize, CellWidthSize,
// CellHeightSize);
// }
// }
// }

// @Override
// public Dimension getPreferredSize() {
// return new Dimension(this.gameBoardWidth, this.gameBoardHeight);
// }

// public void translateActiveCellAllTheWayDown() {
// while (isPossibleTranslation(1, 0)) {
// translateActiveCell(1, 0);
// }
// }
