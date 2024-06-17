import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Plane extends JPanel {
    int planeWidth = 60; //飛機大小
    int planeHeight = 80;
    private int x; //飛機初始位置
    private int y;
    private Window window;
    private Image image;
    public boolean visiable = true;

    public Plane(Window w) { // 建構子
        this.window = w;
        ImageIcon icon = new ImageIcon(getClass().getResource("PlaneImage.jpg"));
        image = icon.getImage();
        x = (1080 - planeWidth) / 2;
        y = 720 - planeHeight - 10;
    }

    public void movePlane() {
        if (x + window.returnKey() < window.getWidth() - planeWidth && x + window.returnKey() > 0) {
            x += window.returnKey();
        } 
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, planeWidth, planeHeight);
    }

    public void paint(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, planeWidth, planeHeight, this);
        }
    }

    public void respawn() {
        this.x = (1080 - planeWidth) / 2;
        this.y = 720 - planeHeight - 10;
    }
}