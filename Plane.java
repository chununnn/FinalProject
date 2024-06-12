import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Plane extends JPanel{
    private JLabel label;
    public Plane() {
        ImageIcon imageTemp = new ImageIcon("12+ Fighter Jet Cartoon Png.jpg");

        int imageWdith = 135;
        int imageHeight = 180;

        Image scaledImage = imageTemp.getImage().getScaledInstance(imageWdith, imageHeight,Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(scaledImage);

        label = new JLabel(image);
        label.setBounds((800 - imageWdith) / 2, 600 - imageHeight - 10, imageWdith, imageHeight);

        add(label);
    }
}