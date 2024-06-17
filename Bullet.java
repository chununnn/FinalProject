import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Bullet extends JPanel{
    public final int bulletSize = 5;
    private int x;
    private int y;
    public final int speed = 7;
    private Plane plane;
    private int index;
    public boolean visiable = true;

    public Bullet(Plane p, int index) {  
        plane = p;
        this.x = plane.getX() + 28;
        this.y = plane.getY() - 15;
        this.index = index;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, bulletSize, bulletSize + 5);
    }

    // 子彈重生
    public void respawn() {
        x = plane.getX() + 28;
        y = plane.getY() - 15;
    }

    public void moveBullet() {
        if (y - speed <= bulletSize + 20) {
            respawn();
        } else {
            //System.err.println("Moving bullet");
            y -= speed;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(new Color(181,230,29));
        g.fillRect(x, y, bulletSize, bulletSize + 5);
    }

    @Override
    public int getY() {
        return y;
    }

    public int getIndex() {
        return this.index;
    }
}
