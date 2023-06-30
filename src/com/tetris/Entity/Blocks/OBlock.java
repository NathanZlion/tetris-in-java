package com.tetris.Entity.Blocks;

import java.awt.Color;

public class OBlock extends Block {
    static final int gridSize = 2;
    static final Color color = new Color(255, 255, 0); // yellow
    static int[][] stateOne = new int[][] { { 1, 1 }, { 1, 1 } };
    static int[][][] setOfStates = new int[][][] { stateOne };

    OBlock() {
        super(gridSize, setOfStates, color);
    }
}

