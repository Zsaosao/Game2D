package object;

import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject {

    public OBJ_Boot(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "boot";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/boot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
