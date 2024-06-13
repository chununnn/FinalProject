import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Enemy extends JPanel{
    private static final int enemySize = 30;
    Random random = new Random();
    int x = random.nextInt(720 - enemySize);
    int y = enemySize;
    int incy = 1; // 這是石頭要移動的單位
    private Window window;

    public Enemy(Window w) {
        this.window = w;
    }

    void moveEnemy() {
        if (y + incy > window.getHeight() - enemySize) {
            x = random.nextInt(window.getWidth() - enemySize);
            y = enemySize;
        } else {
            y += incy;
        }

    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, enemySize, enemySize);
    }
}
