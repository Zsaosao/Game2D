package object;

import javax.imageio.ImageIO;

import controller.Controller;

public class OBJ_Boot extends SuperObject {

    Controller gamePanel;

    public OBJ_Boot(int x, int y, Controller gamePanel) {
        this.worldX = x;
        this.worldY = y;
        this.name = "boot";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/boot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel.objectList.add(this);
    }
}
