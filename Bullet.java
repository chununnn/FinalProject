import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Bullet extends JPanel{
    private final int bulletSize = 5;
    private int x;
    private int y;
    private final int speed = 10;
    private Plane plane;
    public boolean visiable = true;

    public Bullet() {  
        plane = new Plane(null); 
        this.x = plane.getX() + 28;
        this.y = plane.getY() - 15;
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
            y -= speed;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, bulletSize, bulletSize + 5);
    }
}
