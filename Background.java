//Background background = new Background();
//setContentPane(background);
//把這兩行加到建構子就好


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background extends JPanel {

    private Image backgroundImage;
    String ImageName = "space.jpg";
    public Background() {
        try {
            backgroundImage = ImageIO.read(new File(ImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void paint(Graphics g) {
        
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1080, 720, this);
        }
    }
}
