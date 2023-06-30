package com.tetris.Entity;

import java.awt.Color;

public class Cell {
    private Color color = Color.WHITE;
    private Boolean isActive = false; // is falling down
    private Boolean isVisible = false; // can be seen on the screen

    public Cell(Color color, Boolean isActive, Boolean isVisible) {
        this.color = color;
        this.isActive = isActive;
        this.isVisible = isVisible;
    }

    public Cell() {
        color = Color.WHITE;
        isActive = false;
        isVisible = false;

    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}
