package com.tetris.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundsHandler {

    public AudioInputStream mainMusic;
    private Clip clip;

    public SoundsHandler() {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            System.out.println("Sound handler crashed, cannot instantiate sound handler.");
        }
    }

    public void playMusic(AudioInputStream audio) {
        try {
            if (clip.isRunning())
                clip.stop();

            clip.open(audio);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            System.out.printf("Failed to play music %s", e.getMessage());
        }
    }

    public void loadSounds() {
        try {
            String dir = System.getProperty("user.dir")+"\\Assets\\Sounds\\music_zapsplat_easy_cheesy.wav"; 
            File mainMusicFile = new File(dir);
            if (mainMusicFile.exists()){
                mainMusic = AudioSystem.getAudioInputStream(mainMusicFile);
            } else {
                System.out.printf("Music file %s not found!", mainMusicFile.getPath());
            }
        } catch (Exception e) {
            System.out.printf("Cannot Find Music Files. %s", e.getMessage());
        }
    }
}
