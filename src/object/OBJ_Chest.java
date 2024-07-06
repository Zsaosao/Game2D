package object;

import javax.imageio.ImageIO;

import controller.Controller;

public class OBJ_Chest extends SuperObject {

    Controller gamePanel;

    public OBJ_Chest(int x, int y, Controller gamePanel) {
        this.worldX = x;
        this.worldY = y;
        this.name = "chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/chest.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel.objectList.add(this);
    }
}
