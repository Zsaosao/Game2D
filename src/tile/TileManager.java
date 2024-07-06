package tile;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;

public class TileManager {
    Controller gamePanel;
    public List<Tile> tileList;

    public int[][] map;

    public TileManager(Controller gamePanel) {
        this.gamePanel = gamePanel;
        tileList = new ArrayList<>();
        map = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTitleImage();
        loadMap("src\\res\\maps\\worldV2.txt");
        // loadMap("src\\res\\maps\\world012.txt");

    }

    // fix feuture observer
    public void getTitleImage() {
        // tileList.add(new Tile("/res/tiles/grass.png")); // Index 0
        // tileList.add(new Tile("/res/tiles/wall.png", true)); // Index 1
        // tileList.add(new Tile("/res/tiles/water.png", true)); // Index 2
        // tileList.add(new Tile("/res/tiles/earth.png")); // Index 3
        // tileList.add(new Tile("/res/tiles/tree.png", true)); // Index 4
        // tileList.add(new Tile("/res/tiles/sand.png")); // Index 5

        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 0
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 1
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 2
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 3
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 4
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 5
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 6
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 7
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 8
        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 9

        tileList.add(new Tile("/res/tiles/grass00.png")); // Index 10
        tileList.add(new Tile("/res/tiles/grass01.png")); // Index 11
        tileList.add(new Tile("/res/tiles/water00.png", true)); // Index 12
        tileList.add(new Tile("/res/tiles/water01.png", true)); // Index 13
        tileList.add(new Tile("/res/tiles/water02.png", true)); // Index 14
        tileList.add(new Tile("/res/tiles/water03.png", true)); // Index 15
        tileList.add(new Tile("/res/tiles/water04.png", true)); // Index 16
        tileList.add(new Tile("/res/tiles/water05.png", true)); // Index 17
        tileList.add(new Tile("/res/tiles/water06.png", true)); // Index 18
        tileList.add(new Tile("/res/tiles/water07.png", true)); // Index 19
        tileList.add(new Tile("/res/tiles/water08.png", true)); // Index 20
        tileList.add(new Tile("/res/tiles/water09.png", true)); // Index 21
        tileList.add(new Tile("/res/tiles/water10.png", true)); // Index 22
        tileList.add(new Tile("/res/tiles/water11.png", true)); // Index 23
        tileList.add(new Tile("/res/tiles/water12.png", true)); // Index 24
        tileList.add(new Tile("/res/tiles/water13.png", true)); // Index 25
        tileList.add(new Tile("/res/tiles/road00.png")); // Index 26
        tileList.add(new Tile("/res/tiles/road01.png")); // Index 27
        tileList.add(new Tile("/res/tiles/road02.png")); // Index 28
        tileList.add(new Tile("/res/tiles/road03.png")); // Index 29
        tileList.add(new Tile("/res/tiles/road04.png")); // Index 30
        tileList.add(new Tile("/res/tiles/road05.png")); // Index 31
        tileList.add(new Tile("/res/tiles/road06.png")); // Index 32
        tileList.add(new Tile("/res/tiles/road07.png")); // Index 33
        tileList.add(new Tile("/res/tiles/road08.png")); // Index 34
        tileList.add(new Tile("/res/tiles/road09.png")); // Index 35
        tileList.add(new Tile("/res/tiles/road10.png")); // Index 36
        tileList.add(new Tile("/res/tiles/road11.png")); // Index 37
        tileList.add(new Tile("/res/tiles/road12.png")); // Index 38
        tileList.add(new Tile("/res/tiles/earth.png")); // Index 39
        tileList.add(new Tile("/res/tiles/wall.png", true)); // Index 40
        tileList.add(new Tile("/res/tiles/tree.png", true)); // Index 41

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
