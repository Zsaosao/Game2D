package object;

import javax.imageio.ImageIO;

import controller.Controller;

public class OBJ_Door extends SuperObject {

    Controller gamePanel;

    public OBJ_Door(int x, int y, Controller gamePanel) {
        this.worldX = x;
        this.worldY = y;
        this.name = "door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

        gamePanel.objectList.add(this);
    }
}
