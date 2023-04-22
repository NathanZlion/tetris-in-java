package com.tetris.Blocks;

import java.awt.Color;

public class Block {
    public int gridSize;
    private int[][][] setOfStates;
    public int currStateIndex;
    Color color;

    Block(int gridSize, int[][][] setOfStates, Color color) {
        this.gridSize = gridSize;
        this.setOfStates = setOfStates;
        this.currStateIndex = 0;
        setColor(color);
    }

    public void rotate() {
        int nextStateIndex = (this.currStateIndex + 1) % (this.setOfStates.length);
        this.currStateIndex = nextStateIndex;
    }

    public int[][] getNextRotationState() {
        int nextStateIndex = (currStateIndex + 1) % this.setOfStates.length;
        return this.setOfStates[nextStateIndex];
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

    public int[][] getCurrentState() {
        return this.setOfStates[currStateIndex];
    }
    
    private static final Block[] BLOCK_TYPES = { new IBlock(), new JBlock(), new LBlock(),
            new OBlock(), new SBlock(), new TBlock(), new ZBlock() };

    public static Block getRandomBlockType() {
        int randomIndex = (int) (Math.random() * BLOCK_TYPES.length);
        return BLOCK_TYPES[randomIndex];
    }

}
