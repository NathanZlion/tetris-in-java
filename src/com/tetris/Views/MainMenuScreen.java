package com.tetris.Views;

import java.awt.Graphics2D;


public class MainMenuScreen {

    public int selectedOption = 0;
    String options [] = {
        "New Game",
        "Quit"
    };

    MainMenuScreen() {
    }


    public void nextOption() {
        selectedOption ++;
        selectedOption = selectedOption % options.length;
    }

    public void prevOption() {
        selectedOption --;
        selectedOption = selectedOption % options.length;
    }

    public void draw(Graphics2D g2){
        // draws options on the screen
    }    
}
