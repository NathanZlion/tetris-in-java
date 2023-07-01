package com.tetris.Views;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.tetris.GameForm;

public class MainMenuScreen {

    public int selectedOption = 0;
    String options[] = {
            "New Game",
            "Quit"
    };

    private int textHeight = 50;
    private int padding = 10;
    private int menuPageHeight = (options.length * textHeight) + (options.length - 1 * padding);

    public void nextOption() {
        if (selectedOption < options.length - 1) {
            selectedOption++;
        }
        /* selectedOption = selectedOption % options.length; */
    }

    public void prevOption() {
        if (selectedOption > 0) {
            selectedOption--;
        }
        /* selectedOption = selectedOption % options.length; */
    }

    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 40);
        g2.setFont(font);

        int height = (GameForm.FRAME_HEIGHT / 2) - (menuPageHeight / 2);
        for (int index = 0; index < options.length; index++) {
            if (index == selectedOption) {
                g2.drawString("[>]", 20, height);
            } else {
                g2.drawString("[ ]", 20, height);
            }

            g2.drawString(options[index], 140, height);
            height += textHeight + padding;
        }
    }
}
