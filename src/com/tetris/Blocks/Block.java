package com.tetris.Blocks;

import java.awt.Color;

public class Block {
    public int gridSize;
    private int[][][] setOfStates;
    int currStateIndex = 0;
    Color color;

    Block(int gridSize, int[][][] setOfStates, Color color) {
        this.gridSize = gridSize;
        this.setOfStates = setOfStates;
        setColor(color);
    }

    public void rotate() {
        int nextStateIndex = (currStateIndex + 1) % (setOfStates.length);
        currStateIndex = nextStateIndex;
    }

    public int[][] getNextRotationState() {
        int totalStates = setOfStates.length;
        int nextStateIndex = currStateIndex + 1;
        nextStateIndex %= totalStates;

        return setOfStates[nextStateIndex];
    }

    private static final Block[] BLOCK_TYPES = { new IBlock(), new JBlock(), new LBlock(),
            new OBlock(), new SBlock(), new TBlock(), new ZBlock() };

    public static Block getRandomBlockType() {
        int randomIndex = (int) (Math.random() * BLOCK_TYPES.length);
        return BLOCK_TYPES[randomIndex];
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public int[][] getCurrentState(){
        return setOfStates[currStateIndex];
    }
}
