package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controller.Controller;
import object.OBJ_Key;

public class UI {
    Controller gamePanel;
    Font arial_40;
    BufferedImage keyImage;
    boolean isShowMessageBox = false;
    int couniFps;
    String message = "";
    int time;
    int sumFps;

    public UI(Controller gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        keyImage = new OBJ_Key().image;
    }

    public void draw(Graphics2D graphics2d) {
        graphics2d.setFont(arial_40);
        graphics2d.setColor(Color.WHITE);

        graphics2d.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 4, gamePanel.tileSize,
                gamePanel.tileSize, null);

        graphics2d.drawString("x" + gamePanel.player.getHasKey(), 75, 50);

    }

    public void showMessage(String message, int time) {
        this.message = message;
        this.time = time;
        isShowMessageBox = true;
        couniFps = 0;
        sumFps = time * gamePanel.getFPS();
    }

    public void DrawMessageBox(Graphics2D graphics2d) {
        couniFps++;

        if (couniFps > sumFps) {
            isShowMessageBox = false;
            message = "";
        }
        if (message != "") {
            graphics2d.setFont(arial_40);
            graphics2d.setColor(Color.WHITE);
            int textWidth = graphics2d.getFontMetrics().stringWidth(message);
            graphics2d.drawString(message, gamePanel.maxScreenSizeWidth / 2 - textWidth / 2, gamePanel.tileSize * 2);
        }

    }

    public void drawWin(Graphics2D graphics2d) {
        String message = "You Win!";
        graphics2d.setFont(arial_40);

        int textWidth = graphics2d.getFontMetrics().stringWidth(message);
        graphics2d.drawString(message, gamePanel.maxScreenSizeWidth / 2 - textWidth / 2, gamePanel.tileSize * 2);

        String message2 = "Congratulation!";
        Font arial_80 = new Font("Arial", Font.PLAIN, 80);
        graphics2d.setFont(arial_80);
        int textWidth2 = graphics2d.getFontMetrics().stringWidth(message2);
        graphics2d.drawString(message2, gamePanel.maxScreenSizeWidth / 2 - textWidth2 / 2, gamePanel.tileSize * 4);
    }

    public void pause(Graphics2D graphics2d) {
        String message = "Pause";
        graphics2d.setFont(arial_40);
        int textWidth = graphics2d.getFontMetrics().stringWidth(message);
        graphics2d.drawString(message, gamePanel.maxScreenSizeWidth / 2 - textWidth / 2, gamePanel.tileSize * 2);
    }
}
