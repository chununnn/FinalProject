import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Bullet extends JComponent{
    private final int bulletSize = 10;
    private int x;
    private int y;
    private int speed = 10;
    private Plane plane = new Plane(null);

    public Bullet() {
        this.x = plane.x + 25;
        this.y = plane.y - 15;
    }

    public void moveBullet() {
        y -= speed;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, bulletSize, bulletSize + 5);
    }
}
