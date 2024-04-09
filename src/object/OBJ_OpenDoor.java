package object;

import javax.imageio.ImageIO;

public class OBJ_OpenDoor extends SuperObject {

    public OBJ_OpenDoor(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "opendoor";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/opendoor.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
