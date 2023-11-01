package com.tetris;
import com.tetris.utils.SoundsHandler;

public class Tetris {
    public static void main(String[] args) {
        new GameForm().setVisible(true);
        SoundsHandler sounds = new SoundsHandler();
        sounds.loadSounds();
        sounds.playMusic(sounds.mainMusic);
    }
}
