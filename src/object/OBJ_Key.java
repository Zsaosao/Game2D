package object;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        this.name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OBJ_Key(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
