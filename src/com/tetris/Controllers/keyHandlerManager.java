package com.tetris.Controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandlerManager implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, pButtonPressed, enterPressed, spacebarPressed,
            escapePressed;

    @Override
    public void keyTyped(KeyEvent e) { 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // int keyCode = e.getKeyCode();

        // if (keyCode == KeyEvent.VK_UP) {
        //     upPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_DOWN) {
        //     downPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_LEFT) {
        //     leftPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_RIGHT) {
        //     rightPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_ENTER) {
        //     enterPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_P) {
        //     pButtonPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_SPACE) {
        //     spacebarPressed = true;
        // }

        // if (keyCode == KeyEvent.VK_ESCAPE) {
        //     escapePressed = true;
        // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (keyCode == KeyEvent.VK_P) {
            pButtonPressed = true;
        }

        if (keyCode == KeyEvent.VK_SPACE) {
            spacebarPressed = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            escapePressed = true;
        }
    }

    public void reset() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        pButtonPressed = false;
        enterPressed = false;
        spacebarPressed = false;
        escapePressed = false;
    }
}
