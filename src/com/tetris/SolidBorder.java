package com.tetris;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class SolidBorder implements Border {
    private int borderWidth;
    private Color borderColor;
    
    public SolidBorder(int borderWidth, Color borderColor) {
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(borderColor);
        for (int i = 0; i < borderWidth; i++) {
            g.drawRect(x + i, y + i, width - i * 2 - 1, height - i * 2 - 1);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
