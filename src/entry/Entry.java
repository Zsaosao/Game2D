package entry;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entry {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String currentDirection;

    public Rectangle soilArea;
    public int soilAreaDefaultX, soilAreaDefaultY;

    public boolean collision = false;

}
