package model;

import java.awt.Rectangle;

public abstract class Entry {

    public int worldX, worldY;
    public int speed;

    public String currentDirection;

    public Rectangle soilArea;
    public int soilAreaDefaultX, soilAreaDefaultY;

    public boolean collision = false;

}
