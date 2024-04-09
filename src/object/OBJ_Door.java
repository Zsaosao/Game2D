package object;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {

    public OBJ_Door(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
