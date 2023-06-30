package com.tetris.Views;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class MainMenuScreen {

    public int selectedOption = 0;
    String options[] = {
            "New Game",
            "Quit"
    };

    MainMenuScreen() {
    }

    public void nextOption() {
        selectedOption++;
        selectedOption = selectedOption % options.length;
    }

    public void prevOption() {
        selectedOption--;
        selectedOption = selectedOption % options.length;
    }

    public void draw(Graphics2D g2) {
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 40);
        g2.setFont(font);

        int height = 40;
        for (int index = 0; index < options.length; index++) {
            g2.drawString(options[index], 100, height);
            height += 40;
        }
    }
}
