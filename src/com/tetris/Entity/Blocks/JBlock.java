package com.tetris.Entity.Blocks;

import java.awt.Color;

public class JBlock extends Block {
    static final int gridSize = 3;
    static final Color color = new Color(0, 0, 255); // blue
    static int[][] stateOne = new int[][] { { 1, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } };
    static int[][] stateTwo = new int[][] { { 0, 1, 1 }, { 0, 1, 0 }, { 0, 1, 0 } };
    static int[][] stateThree = new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 0, 1 } };
    static int[][] stateFour = new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 1, 1, 0 } };

    static int[][][] setOfStates = new int[][][] { stateOne, stateTwo, stateThree, stateFour };

    JBlock() {
        super(gridSize, setOfStates, color);
    }
}
