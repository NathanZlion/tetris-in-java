package com.tetris.Blocks;

import java.awt.Color;

public class IBlock extends Block {
    static final int gridSize = 4;
    static final Color color = new Color(255, 255, 255); // white
    static int[][] stateOne = new int[][] { { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } };
    static int[][] stateTwo = new int[][] { { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
    static int[][][] setOfStates = new int[][][] { stateOne, stateTwo };

    public IBlock() {
        super(gridSize, setOfStates, color);
    }
}
