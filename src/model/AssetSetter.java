package model;

import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_OpenDoor;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        OBJ_Key key = new OBJ_Key(25 * gamePanel.tileSize, 21 * gamePanel.tileSize);
        gamePanel.objectList.add(key);
        OBJ_Key key2 = new OBJ_Key(23 * gamePanel.tileSize, 40 * gamePanel.tileSize);
        gamePanel.objectList.add(key2);
        OBJ_Key key3 = new OBJ_Key(38 * gamePanel.tileSize, 8 * gamePanel.tileSize);
        gamePanel.objectList.add(key3);

        OBJ_Door door = new OBJ_Door(10 * gamePanel.tileSize, 12 * gamePanel.tileSize);
        gamePanel.objectList.add(door);
        OBJ_Door door2 = new OBJ_Door(8 * gamePanel.tileSize, 28 * gamePanel.tileSize);
        gamePanel.objectList.add(door2);
        OBJ_Door door3 = new OBJ_Door(12 * gamePanel.tileSize, 22 * gamePanel.tileSize);
        gamePanel.objectList.add(door3);

        OBJ_Chest chest = new OBJ_Chest(10 * gamePanel.tileSize, 8 * gamePanel.tileSize);
        gamePanel.objectList.add(chest);

        OBJ_Boot boot = new OBJ_Boot(25 * gamePanel.tileSize, 20 * gamePanel.tileSize);
        gamePanel.objectList.add(boot);

    }

    public void addOpenDoor(int worldX, int worldY) {
        OBJ_OpenDoor openDoor = new OBJ_OpenDoor(worldX, worldY);
        gamePanel.objectList.add(openDoor);
    }
}
