package com.tetris.Entity.Blocks;

import java.awt.Color;

public class LBlock extends Block {
    static final int gridSize = 3;
    static final Color color = new Color(255, 165, 0); // orange
    static int[][] stateOne = new int[][] { { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
    static int[][] stateTwo = new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 } };
    static int[][] stateThree = new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 1, 0, 0 } };
    static int[][] stateFour = new int[][] { { 1, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 } };
    static int[][][] setOfStates = new int[][][] { stateOne, stateTwo, stateThree, stateFour };

    LBlock() {
        super(gridSize, setOfStates, color);
    }
}
