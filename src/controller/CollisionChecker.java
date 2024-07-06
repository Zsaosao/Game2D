package controller;

import model.Player;
import model.Entry;

public class CollisionChecker {
    Controller gamePanel;

    public CollisionChecker(Controller gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Player e) {
        int entryLeftWorldX = e.worldX + e.soilArea.x;
        int entryRightWorldX = e.worldX + e.soilArea.x + e.soilArea.width;
        int entryTopWorldY = e.worldY + e.soilArea.y;
        int entryBottomWorldY = e.worldY + e.soilArea.y + e.soilArea.height;

        int entryLeftCol = entryLeftWorldX / gamePanel.tileSize;
        int entryRightCol = entryRightWorldX / gamePanel.tileSize;
        int entryTopRow = entryTopWorldY / gamePanel.tileSize;
        int entryBottomRow = entryBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (e.currentDirection) {
            case "up":
                entryTopRow = (entryTopWorldY - e.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.map[entryLeftCol][entryTopRow];
                tileNum2 = gamePanel.tileManager.map[entryRightCol][entryTopRow];

                if (gamePanel.tileManager.tileList.get(tileNum1).collision
                        || gamePanel.tileManager.tileList.get(tileNum2).collision) {
                    e.collision = true;
                }
                break;
            case "down":
                entryBottomRow = (entryBottomWorldY + e.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.map[entryLeftCol][entryBottomRow];
                tileNum2 = gamePanel.tileManager.map[entryRightCol][entryBottomRow];

                if (gamePanel.tileManager.tileList.get(tileNum1).collision
                        || gamePanel.tileManager.tileList.get(tileNum2).collision) {
                    e.collision = true;
                }
                break;
            case "left":
                entryLeftCol = (entryLeftWorldX - e.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.map[entryLeftCol][entryTopRow];
                tileNum2 = gamePanel.tileManager.map[entryLeftCol][entryBottomRow];

                if (gamePanel.tileManager.tileList.get(tileNum1).collision
                        || gamePanel.tileManager.tileList.get(tileNum2).collision) {
                    e.collision = true;
                }
                break;
            case "right":
                entryRightCol = (entryRightWorldX + e.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.map[entryRightCol][entryTopRow];
                tileNum2 = gamePanel.tileManager.map[entryRightCol][entryBottomRow];

                if (gamePanel.tileManager.tileList.get(tileNum1).collision
                        || gamePanel.tileManager.tileList.get(tileNum2).collision) {
                    e.collision = true;
                }
                break;
            default:
                break;
        }

    }

    public int checkObject(Entry e, boolean player) {
        int index = -1;
        for (int i = 0; i < gamePanel.objectList.size(); i++) {
            e.soilArea.x = e.soilAreaDefaultX + e.worldX;
            e.soilArea.y = e.soilAreaDefaultY + e.worldY;

            gamePanel.objectList.get(i).soilArea.x = gamePanel.objectList.get(i).worldX
                    + gamePanel.objectList.get(i).soilArea.x;
            gamePanel.objectList.get(i).soilArea.y = gamePanel.objectList.get(i).worldY
                    + gamePanel.objectList.get(i).soilArea.y;

            switch (e.currentDirection) {
                case "up":
                    e.soilArea.y -= e.speed;
                    if (e.soilArea.intersects(gamePanel.objectList.get(i).soilArea)) {
                        if (gamePanel.objectList.get(i).collision) {
                            e.collision = true;
                        }
                        if (player) {
                            index = i;
                        }

                    }
                    break;
                case "down":
                    e.soilArea.y += e.speed;
                    if (e.soilArea.intersects(gamePanel.objectList.get(i).soilArea)) {
                        if (gamePanel.objectList.get(i).collision) {
                            e.collision = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                    break;
                case "left":
                    e.soilArea.x -= e.speed;
                    if (e.soilArea.intersects(gamePanel.objectList.get(i).soilArea)) {
                        if (gamePanel.objectList.get(i).collision) {
                            e.collision = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                    break;
                case "right":
                    e.soilArea.x += e.speed;
                    if (e.soilArea.intersects(gamePanel.objectList.get(i).soilArea)) {
                        if (gamePanel.objectList.get(i).collision) {
                            e.collision = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                    break;

                default:
                    break;

            }
            e.soilArea.x = e.soilAreaDefaultX;
            e.soilArea.y = e.soilAreaDefaultY;
            gamePanel.objectList.get(i).soilArea.x = gamePanel.objectList.get(i).soilAreaDefaultX;
            gamePanel.objectList.get(i).soilArea.y = gamePanel.objectList.get(i).soilAreaDefaultY;
        }
        return index;
    }
}
