

import javax.swing.JFrame;

import controller.Controller;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        Controller gamePanel = new Controller();

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

}
