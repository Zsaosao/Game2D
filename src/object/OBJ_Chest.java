package object;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.name = "chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objs/chest.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
