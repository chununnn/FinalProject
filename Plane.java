import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Image;

public class Plane extends JPanel {
    int planeWidth = 60; //飛機大小
    int planeHeight = 80;
    public int x; //飛機初始位置
    public int y;
    private Window window;
    private Image image;

    public Plane(Window w) { // 建構子
        this.window = w;
        ImageIcon icon = new ImageIcon(getClass().getResource("12+ Fighter Jet Cartoon Png.jpg"));
        image = icon.getImage();
        x = (1080 - planeWidth) / 2;
        y = 720 - planeHeight - 10;
    }

    // 鍵盤輸入要有錯，我要改
    // 你應該不用實作鍵盤，可以去看看Window裡面，有實作了，用Window裡的就好
    // 好，我去用用看

    public void movePlane() {
        if (x + window.returnKey() < window.getWidth() - planeWidth && x + window.returnKey() > 0) {
            x += window.returnKey();
        } 
    }

    public void paint(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, planeWidth, planeHeight, this);
        }
    }
}