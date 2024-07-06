package object;

import javax.imageio.ImageIO;

import controller.Controller;

public class OBJ_Key extends SuperObject {

    Controller gamePanel;

    public OBJ_Key() {
        this.name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OBJ_Key(int x, int y, Controller gamePanel) {
        this.worldX = x;
        this.worldY = y;
        this.name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel.objectList.add(this);
    }
}
