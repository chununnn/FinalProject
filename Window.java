/*
視窗大小是1080*720
 * keyReturn代表使用者輸入 1是左鍵 2是右鍵 0是其他或是無輸入
 * returnKey()回傳輸入
 * bloodMinus()會執行bloodMinusOne()並且顯限於螢幕上，讀去血量是以第一個blood為基準 
 * 
 */



import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame implements KeyListener {
    private int keyReturn;
    private Blood[] bloods;
    private Score score;
    private Button reset;
    private Button close;
    
    private Plane plane;
    private List<Enemy> enemies;
    private List<Bullet> bullets;

    private Timer genTimer;
    private Timer paintTimer;

    Window(String title) {
        this.setTitle(title); // 視窗標題
        this.setSize(1080, 720); // 視窗大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null); // 視窗居中
        this.addKeyListener(this);
        bloods =new Blood[]{new Blood(20),
                 new Blood(50),
                 new Blood(80)};
        score = new Score(0);
        reset = new Button("reset", 390);
        close = new Button("close", 550);
        this.addMouseListener(reset);
        this.addMouseListener(close);
        reset.setActionListener(() -> {
        /////////////////////////////////////////////////////////////////  這一區塊是按下reset之後執行的
            bloods =new Blood[]{new Blood(20),
                new Blood(50),
                new Blood(80)};
            this.score.resetScore(); 
            this.reset.setVisible(false);
            this.close.setVisible(false);
            repaint();
        //////////////////////////////////////////////////////////////// 
        });
        close.setActionListener(() -> {
            //////////////////////// 這是按下close之後之行的
            dispose();
            ///////////////////////
        });
        this.setVisible(true); // 顯示視窗
        //test
        this.enemies = new ArrayList<>();
        genTimer = new Timer(500, new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
            Enemy stone = new Enemy(Window.this);
            Window.this.enemies.add(stone);
            repaint();
            System.err.println("Generating.");
           } 
        });
        
        genTimer.start();
        
         paintTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Enemy enemy : Window.this.enemies) {
                    enemy.moveEnemy();
                    //System.err.println("Moving.");
                }
                repaint();
                System.err.println("time delay is 16.");
            }
        });

        paintTimer.start(); 
    }

    // 處理鍵盤輸入
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 左
            plane.keyPressedLeft(e);
            keyReturn = 1;
            System.out.println(1);
            
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 右
            plane.keyPressedRight(e);
            keyReturn = 2;
            System.out.println(2);
        } else {
            keyReturn = 0;
            System.out.println(0);
        }
        
    }

    public void keyReleased(KeyEvent e) {
        //plane.keyReleased(e);
        keyReturn = 0;
        System.out.println(0);
        
    }

    public int returnKey() {
        return keyReturn;
    }

    // 所有會出現在螢幕上的物件都要draw坐在這邊，每個物件 ***一定要有實作draw()***
    public void paint(Graphics g) {
        System.err.println("Painting.");
        super.paint(g); 
        Graphics2D g2d = (Graphics2D) g;
        for(Blood blood : bloods){
            blood.draw(g);
        }
        score.draw(g);
        reset.draw(g);
        close.draw(g);

        //plane.paint(g2d);
        for(Enemy enemy : enemies) {
            enemy.paint(g2d);
        }
        //bullet.paint(g2d);
    }

    public void bloodMinus(){
            bloods[0].bloodMinusOne();
            int index = bloods[0].getBlood();  //以第一個blood為基準
            bloods[index].eliminate();
            if(index == 0){     //血量歸零之後出現按鈕
                this.reset.setVisible(true);
                this.close.setVisible(true);
            }
    }

    /*public void move() {
        bullet.moveBullet();
        enemy.moveEnemy();
    }*/
/* 
    public void determine() {
        for(Bullet bullet : bullets) {
            for(Enemy enemy : enemies) {
                if(collision(bullet.getBound(), enemy.getBound())) {
                    //bullet disappeared
                    enemy.EBloodMinusOne();
                    score.scorePlus();;
                    if(enemy.getBlood() == 0) {
                        //enemy disappeard
                    }
                } else if(collision(bullet.getBound(), window.getBoundUp())) {
                    //bullet disappeared
                }
            }
        }

        for(Enemy enemy : enemies) {
            if(collision(plane.getBound(), enemy.getBound())) {
                bloodMinus();
                //enemy disappeared
            } else if(collision(enemy.getBound(), window.getBoundDown())) {
                //enemy disappeared
                score.scoreMinus();
            }
        }
    }
    */
    private boolean collision(Rectangle R1, Rectangle R2) {
        return R1.intersects(R2);
    }

    
    
    public static void main(String[] args) throws InterruptedException {
        /*Window window = */new Window("test");
        /*while (true) {
            window.move();
        }*/
        
    }
    
}