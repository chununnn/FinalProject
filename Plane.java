import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends JPanel {
    int planeWidth = 60; //飛機大小
    int planeHeight = 80;
    int x = (1600 - planeWidth) / 2; //飛機初始位置
    int y = 1200 - planeHeight - 200;
    private Window window;
    private Image image;

    public Plane(Window w) { // 建構子
        this.window = w;
        ImageIcon icon = new ImageIcon(getClass().getResource("12+ Fighter Jet Cartoon Png.jpg"));
        image = icon.getImage();
    }

    int xa = 0;

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -200;
        } 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 200;
        } 
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void movePlane() {
        if (x + xa < window.getWidth() - planeWidth && x + xa > 0) {
            x += xa;
        }
    }

    public void paint(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, planeWidth, planeHeight, this);
        }
    }
}