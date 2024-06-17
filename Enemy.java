import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends JPanel{
    public final int enemySize = 30;
    Random random = new Random();
    int x = random.nextInt(720 - enemySize);
    int y = enemySize;
    public int enemyblood = 5;
    private long speed; // 這是石頭要移動的單位
    private Window window;
    private int index;
    public boolean visiable = true;

    public Enemy(Window w, int index, long speed) {
        this.index = index;
        this.window = w;
        this.speed = speed;
    }

    public int EBloodMinusOne() {
        return enemyblood -= 1;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, enemySize, enemySize);
    }

    // 石頭重生
    public void respawn() {
        x = random.nextInt(window.getWidth() - enemySize);
        y = enemySize;
    }

    public void moveEnemy() {
        if (y + speed > window.getHeight() - enemySize) {
            respawn();
        } else {
            y += speed;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, enemySize, enemySize);
    }

    @Override
    public int getY() {
        return this.y;
    }

    public int getIndex() {
        return this.index;
    }
}