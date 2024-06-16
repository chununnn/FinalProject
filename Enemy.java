import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends JPanel{
    public final int enemySize = 30;
    Random random = new Random();
    //int x = random.nextInt(720 - enemySize);
    int x = 528;
    int y = enemySize;
    public int enemyblood = 5;
    private final int speed = 10; // 這是石頭要移動的單位
    private Window window;
    public boolean visiable = true;

    public Enemy(Window w) {
        this.window = w;
    }

    public int EBloodMinusOne() {
        return enemyblood -= 1;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, enemySize, enemySize);
    }

    // 檢測有無跟飛機碰撞
    private boolean collisionPlane() {
        return window.plane.getBounds().intersects(getBounds());
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

        if (collisionPlane()) {
            window.bloodMinus();
            respawn();
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, enemySize, enemySize);
    }
}

