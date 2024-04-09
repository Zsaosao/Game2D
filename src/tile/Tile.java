package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
    public boolean collision;
    public BufferedImage bufferedImage;

    public Tile(String path) {
        try {
            this.bufferedImage = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading tile image");
        }
        this.collision = false;
    }

    public Tile(String path, boolean collision) {
        try {
            this.bufferedImage = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collision = collision;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}
