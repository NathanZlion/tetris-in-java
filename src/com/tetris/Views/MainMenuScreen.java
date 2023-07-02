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
        selectedOption++;
        if (selectedOption > options.length - 1) {
            selectedOption = selectedOption - options.length;
        }
    }

    public void prevOption() {
        selectedOption--;
        if (selectedOption < 0) {
            selectedOption = selectedOption + options.length;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 50);
        g2.setFont(font);
        
        font = new Font("Serif", Font.PLAIN, 40);
        drawXCenteredText(g2, "TETRIS GAME", 100);
        g2.setFont(font);

        int height = (GameForm.FRAME_HEIGHT / 2) - (menuPageHeight / 2);
        for (int index = 0; index < options.length; index++) {
            g2.drawString(index == selectedOption ? "[>]" : "[  ]", 20, height);
            String option = options[index];
            drawXCenteredText(g2, option, height);
            height += textHeight + padding;
        }
    }

    /**
     * Draws a string centered horizontally on the screen. Vertical position on 
     * the screen will have to be inputted.
     * 
     * @param graphics2DImage
     * @param text
     * @param verticalPosition
     */
    private static void drawXCenteredText(Graphics2D graphics2DImage, String text, int verticalPosition) {
        int stringWidthLength = (int) graphics2DImage.getFontMetrics().getStringBounds(text, graphics2DImage).getWidth();
        int horizontalCenter = GameForm.FRAME_WIDTH / 2 - stringWidthLength / 2;

        graphics2DImage.drawString(text, horizontalCenter, verticalPosition);
    }
}
