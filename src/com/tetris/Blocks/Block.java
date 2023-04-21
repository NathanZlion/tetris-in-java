package com.tetris.Blocks;

import java.awt.Color;

import com.tetris.Cell;

public class Block {
    Cell[][] shape;
    int gridSize;
    private int[][][] setOfStates;
    int currStateIndex = 0;

    Block(int gridSize, int[][][] setOfStates, Color color) {
        this.gridSize = gridSize;
        this.shape = new Cell[gridSize][gridSize];
        this.setOfStates = setOfStates;
    }

    public void rotate() {
        int totalStates = setOfStates.length;
        int nextStateIndex = currStateIndex + 1;
        nextStateIndex %= totalStates;
        currStateIndex = nextStateIndex;

        setShapeToCurrentState();
    }

    public void setShapeToCurrentState() {
        int[][] currState = setOfStates[currStateIndex];

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                shape[row][col].setIsVisible(currState[row][col] == 1 ? true : false);
            }
        }
    }

    public int[][] getNextRotationState() {
        int totalStates = setOfStates.length;
        int nextStateIndex = currStateIndex + 1;
        nextStateIndex %= totalStates;

        return setOfStates[nextStateIndex];
    }

    public void makeCellsInactive() {
        for (Cell[] row : shape) {
            for (Cell c : row) {
                c.setIsActive(false);
            }
        }
    }
    // get move down state : move all cells one step down return None;

    // move down : return the state of moved down block, return type cell[][];

    Block getRandomBlockType() {
        int randomInt = Math.round((float) Math.random() * 6);
        switch (randomInt) {
            case 0:
                return new IBlock();
            case 1:
                return new JBlock();
            case 2:
                return new LBlock();
            case 3:
                return new OBlock();
            case 4:
                return new SBlock();
            case 5:
                return new TBlock();
            case 6:
                return new ZBlock();
            default:
                return new IBlock();
        }
    }
}
