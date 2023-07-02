package com.tetris.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class HighScoreHandler {

    public String highScoreFileString = "../lib/highScore.txt";
    public int highscore;

    public HighScoreHandler() {
        highscore = readHighScore();
    }

    /**
     * Reads the high score from the high score file
     * 
     * @return highScoreValue
     */
    public int readHighScore() {
        int defaultValue = 0;
        try {
            InputStream inputStream = getClass().getResourceAsStream(highScoreFileString);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String nextLine = bufferedReader.readLine();
            highscore = Integer.parseInt(nextLine);
            return highscore;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;
    }

    /**
     * Writes the new high score to the high score file
     * 
     * @param score
     */
    public void writeHighScore(int score) {
        if (score < highscore) {
            return;
        }
        highscore = score;
        try {
            String filePath = "src/com/tetris/lib/highScore.txt";
            File file = new File(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the high score: " + e.getMessage());
        }
    }

}
