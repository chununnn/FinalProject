import java.awt.Rectangle;

public class AirplaneBoomBoom {
    public static void main(String[] args) {
        Window window = new Window("AirplaneBoomBoom", 400, 600);

        if(window.returnKey() == 1 || window.returnKey() == 2) {
            Plane plane = new Plane(window);
            Blood blood = new Blood();
        }

        //in game
        while(blood.getBlood() != 0) {
            /*
            all objects move 
             */

            for(Bullet bullet : bullets) {
                for(Enemy enemy : enemies) {
                    if(collision(bullet.getBound(), enemy.getBound())) {
                        /* bullet disappeared */
                        enemy.EBloodMinusOne();
                        score.setPoint(1);
                        if(enemy.getBlood() == 0) {
                            /* enemy disappeard */
                        }
                    } else if(collision(bullet.getBound(), window.getBoundUp())) {
                        /* bullet disappeared */
                    }
                }
            }

            for(Enemy enemy : enemies) {
                if(collision(plane.getBound(), enemy.getBound())) {
                    blood.bloodMinusOne();
                    /* enemy disappeared */
                } else if(collision(enemy.getBound(), window.getBoundDown())) {
                    /* enemy disappeared */
                    score.setPoint(-5);
                }
            }
        }
    }

    private boolean collision(Rectangle R1, Rectangle R2) {
        return R1.intersects(R2);
    }
}