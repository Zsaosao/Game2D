package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controller.Controller;
import controller.KeyHandler;
import object.SuperObject;

import java.awt.Rectangle;

public class Player extends Entry {

    Controller gamePanel;
    KeyHandler keyHandler;

    int spriteIndex = 0;
    int spriteDelay = 12; // 12 frames per sprite
    int spriteDelayCounter = 0;

    // camera center
    public int screenX;
    public int screenY;

    // Item in bag
    int hasKey = 0;

    IMove iMove;

    public Player(Controller gamePanel) {
        this.gamePanel = gamePanel;
        setDefaultLocation();

    }

    public void setDefaultLocation() {
        worldX = 23 * gamePanel.tileSize;
        worldY = 21 * gamePanel.tileSize;
        speed = 4;
        currentDirection = "down";
        // Strategy pattern set movement type by foot for player or set movement type by
        // wing or car in the future
        iMove = new MoveByFoot(speed);

        // set collision area
        soilArea = new Rectangle(0, 0, 20, 20);
        soilAreaDefaultX = soilArea.x;
        soilAreaDefaultY = soilArea.y;

        // set camera center
        screenX = gamePanel.maxScreenSizeWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.maxScreenSizeHeight / 2 - gamePanel.tileSize / 2;

    }

    public void update(String direction) {
        if (direction != "stop") {

            currentDirection = direction;

            collision = false;
            gamePanel.collisionChecker.checkTile(this);

            this.pickUpObj();
            this.printItem();

            if (collision == false) {
                if (direction == "up") {
                    worldY -= speed;
                }
                if (direction == "down") {
                    worldY += speed;
                }
                if (direction == "left") {
                    worldX -= speed;
                }
                if (direction == "right") {
                    worldX += speed;
                }
            }

            spriteDelayCounter++;
            if (spriteDelayCounter >= spriteDelay) {
                spriteIndex = (spriteIndex == 0) ? 1 : 0;
                spriteDelayCounter = 0;
            }

        }
        // model call controller
        gamePanel.repaint();

    }

    public void draw(Graphics2D g2d) {
        BufferedImage playerImage = null;
        switch (currentDirection) {
            case "up":
                if (spriteIndex == 0) {
                    playerImage = iMove.getUp1();
                } else {
                    playerImage = iMove.getUp2();
                }
                break;
            case "down":
                if (spriteIndex == 0) {
                    playerImage = iMove.getDown1();
                } else {
                    playerImage = iMove.getDown2();
                }

                break;
            case "left":
                if (spriteIndex == 0) {
                    playerImage = iMove.getLeft1();
                } else {
                    playerImage = iMove.getLeft2();
                }
                break;
            case "right":
                if (spriteIndex == 0) {
                    playerImage = iMove.getRight1();
                } else {
                    playerImage = iMove.getRight2();
                }
                break;
            default:
                break;
        }

        g2d.drawImage(playerImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }

    public void printItem() {
        System.out.println("Key: " + hasKey + "Speed: " + speed);
    }

    public void pickUpObj() {
        int objIndex = gamePanel.collisionChecker.checkObject(this, true);
        if (objIndex != -1) {
            SuperObject obj = gamePanel.objectList.get(objIndex);
            if (obj.name == "key") {
                hasKey++;
                gamePanel.objectList.remove(objIndex);
                gamePanel.soundEffect.playSound(1);
                gamePanel.ui.showMessage("You got a key!", 2);
            } else if (obj.name == "door") {
                if (hasKey > 0) {
                    hasKey--;
                    // add open door
                    gamePanel.assetSetter.addOpenDoor(obj.worldX, obj.worldY);

                    // remove key
                    gamePanel.objectList.remove(objIndex);

                    gamePanel.soundEffect.playSound(3);

                }
            } else if (obj.name == "chest") {
                gamePanel.soundEffect.playSound(1);
                gamePanel.setGameFinish();

            } else if (obj.name == "boot") {
                this.speed += 2;
                gamePanel.objectList.remove(objIndex);
                gamePanel.soundEffect.playSound(2);
                gamePanel.ui.showMessage("You get a running boost !", 2);
            }

        }

    }

    public int getHasKey() {
        return hasKey;
    }

}
