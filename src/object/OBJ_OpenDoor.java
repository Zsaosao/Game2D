package object;

import javax.imageio.ImageIO;

import controller.Controller;

public class OBJ_OpenDoor extends SuperObject {

    Controller gamePanel;

    public OBJ_OpenDoor(int x, int y, Controller gamePanel) {
        this.worldX = x;
        this.worldY = y;
        this.name = "opendoor";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/opendoor.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel.objectList.add(this);
    }
}
