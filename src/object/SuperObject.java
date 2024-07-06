package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import controller.Controller;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public int worldX;
    public int worldY;
    public boolean collision;
    public Rectangle soilArea = new Rectangle(0, 0, 48, 48);
    public int soilAreaDefaultX = soilArea.x;
    public int soilAreaDefaultY = soilArea.y;

    public void draw(Graphics2D g, Controller gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.maxScreenSizeWidth / 2;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.maxScreenSizeHeight / 2;
        if (worldX + 2 * gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - 2 * gamePanel.tileSize < gamePanel.player.worldX + gamePanel.maxScreenSizeWidth
                && worldY + 2 * gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - 2 * gamePanel.tileSize < gamePanel.player.worldY + gamePanel.maxScreenSizeHeight) {
            g.drawImage(image, screenX, screenY, gamePanel.tileSize,
                    gamePanel.tileSize, null);

        }
    }

}
