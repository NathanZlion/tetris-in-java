package com.tetris.Blocks;

import java.awt.Color;

public class ZBlock extends Block {
    static final int gridSize = 3;
    static final Color color = new Color(255, 255, 255); // white
    static int[][] stateOne = new int[][] { { 1, 1, 0 }, { 0, 1, 1 }, { 0, 0, 0 } };
    static int[][] stateTwo = new int[][] { { 0, 0, 1 }, { 0, 1, 1 }, { 0, 1, 0 } };

    static int[][][] setOfStates = new int[][][] { stateOne, stateTwo };

    ZBlock() {
        super(gridSize, setOfStates, color);
    }
}
