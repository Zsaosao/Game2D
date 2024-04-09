package tile;

import java.util.ArrayList;
import java.util.List;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;

import main.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    public List<Tile> tileList;

    public int[][] map;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tileList = new ArrayList<>();
        map = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTitleImage();
        loadMap("src\\res\\maps\\world01.txt");
        // loadMap("src\\res\\maps\\world012.txt");

    }

    // fix feuture observer
    public void getTitleImage() {
        tileList.add(new Tile("/res/tiles/grass.png")); // Index 0
        tileList.add(new Tile("/res/tiles/wall.png", true)); // Index 1
        tileList.add(new Tile("/res/tiles/water.png", true)); // Index 2
        tileList.add(new Tile("/res/tiles/earth.png")); // Index 3
        tileList.add(new Tile("/res/tiles/tree.png", true)); // Index 4
        tileList.add(new Tile("/res/tiles/sand.png")); // Index 5
    }

    public void loadMap(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int row = 0;
            String line = br.readLine();
            while (line != null) {
                String[] tokens = line.split(" ");
                for (int col = 0; col < tokens.length; col++) {
                    map[col][row] = Integer.parseInt(tokens[col]);
                }
                row++;
                line = br.readLine();
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (int i = 0; i < gamePanel.maxWorldCol; i++) {
            for (int j = 0; j < gamePanel.maxWorldRow; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void draw(Graphics2D g) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileIndex = map[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;

            int screenX = worldX - gamePanel.player.worldX + gamePanel.maxScreenSizeWidth / 2;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.maxScreenSizeHeight / 2;
            if (worldX + 2 * gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                    && worldX - 2 * gamePanel.tileSize < gamePanel.player.worldX + gamePanel.maxScreenSizeWidth
                    && worldY + 2 * gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                    && worldY - 2 * gamePanel.tileSize < gamePanel.player.worldY + gamePanel.maxScreenSizeHeight) {
                g.drawImage(tileList.get(tileIndex).getBufferedImage(), screenX, screenY, gamePanel.tileSize,
                        gamePanel.tileSize, null);

            }

            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
