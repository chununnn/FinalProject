import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends JPanel {
    int planeWidth = 60; //飛機大小
    int planeHeight = 80;
    public int x = (1080 - planeWidth) / 2; //飛機初始位置
    public int y = 720 - planeHeight - 10;
    private Window window;
    private Image image;

    public Plane(Window w) { // 建構子
        this.window = w;
        ImageIcon icon = new ImageIcon(getClass().getResource("12+ Fighter Jet Cartoon Png.jpg"));
        image = icon.getImage();
    }

    // 鍵盤輸入要有錯，我要改
    // 你應該不用實作鍵盤，可以去看看Window裡面，有實作了，用Window裡的就好

    public void keyPressedLeft(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37: setLocation(x - 2, y);
                break;
        }
    }

    public void keyPressedRight(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39: setLocation(x + 2, y);
        }
    }

    public void keyReleased(KeyEvent e) {
        //xa = 0;
    }

    public void movePlane() {
        //if (x + xa < window.getWidth() - planeWidth && x + xa > 0) {
            //x += xa;
        //}
    }

    public void paint(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, planeWidth, planeHeight, this);
        }
    }
}