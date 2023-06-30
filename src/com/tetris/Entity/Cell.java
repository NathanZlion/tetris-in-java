package com.tetris.Entity;

import java.awt.Color;

public class Cell {
    private Color color;
    private Boolean isActive;
    private Boolean isVisible;
    
    public Cell(Color color, Boolean isActive, Boolean isVisible){
        this.color = color;
        this.isActive = isActive;
        this.isVisible = isVisible;
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
