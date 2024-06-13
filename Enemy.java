import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends JPanel{
    private static final int enemySize = 30;
    Random random = new Random();
    int x = random.nextInt(720 - enemySize);
    int y = enemySize;
    int incy = 3; // 這是石頭要移動的單位
    private Window window;

    public Enemy(Window w) {
        this.window = w;
    }

    public void moveEnemy() {
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

