import java.awt.Rectangle;

public class AirplaneBoomBoom {
    public static void main(String[] args) {

    }

    private boolean collision(Rectangle R1, Rectangle R2) {
        return R1.intersects(R2);
    }
}