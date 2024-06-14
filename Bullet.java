import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Bullet extends JPanel{
    private final int bulletSize = 5;
    private int x;
    private int y;
    private final int speed = 10;
    private Plane plane = new Plane(null);

    public Bullet() {
        this.x = plane.x + 28;
        this.y = plane.y - 15;
    }

    public void moveBullet() {
        if (y - speed <= 0 + bulletSize + 20) {
            x = plane.x + 28;
            y = plane.y - 15;
        } else {
            y -= speed;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, bulletSize, bulletSize + 5);
    }
}
