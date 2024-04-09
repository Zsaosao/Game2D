package main;

import javax.swing.JPanel;

import entry.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48

    public final int maxScreenSizeCol = 16;
    public final int maxScreenSizeRow = 12;

    public final int maxScreenSizeWidth = tileSize * maxScreenSizeCol; // 768px
    public final int maxScreenSizeHeight = tileSize * maxScreenSizeRow; // 576px

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    public Player player = new Player(this, keyHandler);
    public TileManager tileManager = new TileManager(this);
    public collisionChecker collisionChecker = new collisionChecker(this);
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

    public GamePanel() {
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
                repaint();
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
        player.update();

    }

    // paintComponent is called by repaint()
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        tileManager.draw(g2d); // zindex 0
        for (int i = 0; i < objectList.size(); i++) {
            objectList.get(i).draw(g2d, this);
        } // zindex 5

        player.draw(g2d); // zindex 10
        ui.draw(g2d); // zindex 15
        ui.DrawMessageBox(g2d); // zindex 20

        if (isGameFinish) {
            ui.drawWin(g2d); // zindex 25
            music.stopSound();
            soundEffect.playSound(4);
            this.gameThread = null;

        }

        g2d.dispose(); // release resources

    }

    public int getFPS() {
        return FPS;
    }

    public void setGameFinish() {
        this.isGameFinish = true;
    }
   

}
