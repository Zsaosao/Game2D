package view;

import javax.swing.JPanel;

import controller.Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class View extends JPanel {
    Controller gamePanel;

    public View(Controller gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void paintComponent(Graphics g) {
        if (gamePanel.pause) {
            gamePanel.ui.pause((Graphics2D) g);
            return;
        }
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        gamePanel.tileManager.draw(g2d); // zindex 0
        for (int i = 0; i < gamePanel.objectList.size(); i++) {
            gamePanel.objectList.get(i).draw(g2d, gamePanel);
        } // zindex 5

        gamePanel.player.draw(g2d); // zindex 10
        gamePanel.ui.draw(g2d); // zindex 15
        gamePanel.ui.DrawMessageBox(g2d); // zindex 20

        if (gamePanel.isGameFinish) {
            gamePanel.ui.drawWin(g2d); // zindex 25
            gamePanel.music.stopSound();
            gamePanel.soundEffect.playSound(4);
            gamePanel.gameThread = null;

        }

        g2d.dispose(); // release resources

    }
}
