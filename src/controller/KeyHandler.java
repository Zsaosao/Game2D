package controller;
// rename to Controller

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    Controller gamePanel;

    public KeyHandler(Controller gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            gamePanel.setDirection("up");
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            gamePanel.setDirection("left");
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            gamePanel.setDirection("down");
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            gamePanel.setDirection("right");
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            gamePanel.pause = !gamePanel.pause;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            gamePanel.setDirection("stop");
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            gamePanel.setDirection("stop");
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            gamePanel.setDirection("stop");
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            gamePanel.setDirection("stop");
        }
    }

}
