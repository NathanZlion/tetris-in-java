package com.tetris.Blocks;

import java.awt.Color;

public class TBlock extends Block {
    static final int gridSize = 3;
    static final Color color = new Color(255, 255, 255); // white
    static int[][] stateOne = new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 0, 0 } };
    static int[][] stateTwo = new int[][] { { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 0 } };
    static int[][] stateThree = new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
    static int[][] stateFour = new int[][] { { 0, 1, 0 }, { 1, 1, 0 }, { 0, 1, 0 } };

    static int[][][] setOfStates = new int[][][] { stateOne, stateTwo, stateThree, stateFour };

    TBlock() {
        super(gridSize, setOfStates, color);
    }
}