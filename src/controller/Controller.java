package controller;


import javax.swing.JPanel;

import model.Player;
import music.Sound;
import object.SuperObject;
import tile.TileManager;
import view.UI;
import view.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Controller extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48

    public final int maxScreenSizeCol = 16;
    public final int maxScreenSizeRow = 12;

    public final int maxScreenSizeWidth = tileSize * maxScreenSizeCol; // 768px
    public final int maxScreenSizeHeight = tileSize * maxScreenSizeRow; // 576px

    public Thread gameThread;
    KeyHandler keyHandler = new KeyHandler(this);

    public Player player = new Player(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public List<SuperObject> objectList = new ArrayList<SuperObject>();

    public Sound music = new Sound();
    public Sound soundEffect = new Sound();

    public AssetSetter assetSetter = new AssetSetter(this); // set objectList

    int FPS = 60;

    final int ONESECOND = 1000000000; // 1 second in nanoseconds

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public boolean isGameFinish = false;

    public UI ui = new UI(this);

    public boolean pause = false;

    View view = new View(this);

    String direction = "stop";

    public void startMusic() {
        music.setSound(0);
        music.loopSound();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        assetSetter.setObject();
        gameThread.start();
        startMusic();
    }

    public Controller() {
        this.setPreferredSize(new Dimension(maxScreenSizeWidth, maxScreenSizeHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.setFocusable(true); // allow keyListener to work
    }

    @Override
    public void run() {
        double drawInterval = ONESECOND / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;
        int timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (deltaTime >= 1) {
             update();
                deltaTime--;
                drawCount++;
            }
            // FPS counter
            if (timer >= ONESECOND) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        if (pause) {
            return;
        }
        player.update(direction);

    }

    // paintComponent is called by repaint() player called repaint()
    public void paintComponent(Graphics g) {
        view.paintComponent(g);

    }

    public int getFPS() {
        return FPS;
    }

    public void setGameFinish() {
        this.isGameFinish = true;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
