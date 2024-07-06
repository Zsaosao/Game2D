package controller;

import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_OpenDoor;

public class AssetSetter {
    Controller gamePanel;

    public AssetSetter(Controller gamePanel) {
        this.gamePanel = gamePanel;
    }

    // observe the setObject method
    public void setObject() {
        new OBJ_Key(25 * gamePanel.tileSize, 21 * gamePanel.tileSize, gamePanel);
        new OBJ_Key(23 * gamePanel.tileSize, 40 * gamePanel.tileSize, gamePanel);
        new OBJ_Key(38 * gamePanel.tileSize, 8 * gamePanel.tileSize, gamePanel);

        new OBJ_Door(10 * gamePanel.tileSize, 12 * gamePanel.tileSize, gamePanel);
        new OBJ_Door(8 * gamePanel.tileSize, 28 * gamePanel.tileSize, gamePanel);
        new OBJ_Door(12 * gamePanel.tileSize, 22 * gamePanel.tileSize, gamePanel);

        new OBJ_Chest(10 * gamePanel.tileSize, 8 * gamePanel.tileSize, gamePanel);

        new OBJ_Boot(25 * gamePanel.tileSize, 20 * gamePanel.tileSize, gamePanel);

    }

    // observe the addOpenDoor method
    public void addOpenDoor(int worldX, int worldY) {
        OBJ_OpenDoor openDoor = new OBJ_OpenDoor(worldX, worldY, gamePanel);
        gamePanel.objectList.add(openDoor);
    }
}
