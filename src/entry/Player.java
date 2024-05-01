package entry;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controller.KeyHandler;
import model.GamePanel;
import object.SuperObject;

import java.awt.Rectangle;

public class Player extends Entry {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    int spriteIndex = 0;
    int spriteDelay = 12; // 12 frames per sprite
    int spriteDelayCounter = 0;

    // camera center
    public int screenX;
    public int screenY;

    // Item in bag
    int hasKey = 3;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultLocation();

    }

    public void setDefaultLocation() {
        worldX = 23 * gamePanel.tileSize;
        worldY = 21 * gamePanel.tileSize;
        speed = 4;
        currentDirection = "down";
        getPlayerImage();

        // set collision area
        soilArea = new Rectangle(0, 0, 20, 20);
        soilAreaDefaultX = soilArea.x;
        soilAreaDefaultY = soilArea.y;

        // set camera center
        screenX = gamePanel.maxScreenSizeWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.maxScreenSizeHeight / 2 - gamePanel.tileSize / 2;

    }

    public void update() {
        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {
            if (keyHandler.up) {
                currentDirection = "up";
            }
            if (keyHandler.down) {
                currentDirection = "down";

            }
            if (keyHandler.left) {
                currentDirection = "left";

            }
            if (keyHandler.right) {
                currentDirection = "right";

            }
            collision = false;
            gamePanel.collisionChecker.checkTile(this);

            this.pickUpObj();
            this.printItem();

            if (collision == false) {
                if (keyHandler.up) {
                    worldY -= speed;
                }
                if (keyHandler.down) {
                    worldY += speed;
                }
                if (keyHandler.left) {
                    worldX -= speed;
                }
                if (keyHandler.right) {
                    worldX += speed;
                }
            }

            spriteDelayCounter++;
            if (spriteDelayCounter >= spriteDelay) {
                spriteIndex = (spriteIndex == 0) ? 1 : 0;
                spriteDelayCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2d) {
        BufferedImage playerImage = null;
        switch (currentDirection) {
            case "up":
                if (spriteIndex == 0) {
                    playerImage = up1;
                } else {
                    playerImage = up2;
                }
                break;
            case "down":
                if (spriteIndex == 0) {
                    playerImage = down1;
                } else {
                    playerImage = down2;
                }

                break;
            case "left":
                if (spriteIndex == 0) {
                    playerImage = left1;
                } else {
                    playerImage = left2;
                }
                break;
            case "right":
                if (spriteIndex == 0) {
                    playerImage = right1;
                } else {
                    playerImage = right2;
                }
                break;
            default:
                break;
        }

        g2d.drawImage(playerImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResource("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("/res/player/boy_up_2.png"));

            down1 = ImageIO.read(getClass().getResource("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("/res/player/boy_down_2.png"));

            left1 = ImageIO.read(getClass().getResource("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("/res/player/boy_left_2.png"));

            right1 = ImageIO.read(getClass().getResource("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("/res/player/boy_right_2.png"));

        } catch (Exception e) {
            System.out.println("Error loading player images");
            e.printStackTrace();
        }

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
