package model;

import javax.imageio.ImageIO;

public class MoveByFoot extends IMove {

    public MoveByFoot(int speed) {
        this.speed = speed;
        getPlayerImage();
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

}
