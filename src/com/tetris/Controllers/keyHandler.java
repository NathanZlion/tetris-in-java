package com.tetris.Controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, pButtonTouched, enterTouched;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP)
            upPressed = true;

        if (keyCode == KeyEvent.VK_DOWN)
            downPressed = true;

        if (keyCode == KeyEvent.VK_LEFT)
            leftPressed = true;

        if (keyCode == KeyEvent.VK_RIGHT)
            rightPressed = true;

        if (keyCode == KeyEvent.VK_ENTER)
            enterTouched = true;

        if (keyCode == KeyEvent.VK_P)
            pButtonTouched = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP)
            upPressed = false;

        if (keyCode == KeyEvent.VK_DOWN)
            downPressed = false;

        if (keyCode == KeyEvent.VK_LEFT)
            leftPressed = false;

        if (keyCode == KeyEvent.VK_RIGHT)
            rightPressed = false;

        if (keyCode == KeyEvent.VK_ENTER)
            enterTouched = false;

        if (keyCode == KeyEvent.VK_P)
            pButtonTouched = false;
    }

}
