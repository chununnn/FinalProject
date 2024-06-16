/*
視窗大小是1080*720
 * keyReturn代表使用者輸入 1是左鍵 2是右鍵 0是其他或是無輸入
 * returnKey()回傳輸入
 * bloodMinus()會執行bloodMinusOne()並且顯限於螢幕上，讀去血量是以第一個blood為基準 
 * 
 */



import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Window extends JFrame implements KeyListener {
    private static final int width = 1080;
    private static final int height = 720;

    private int keyReturn;
    private Blood[] bloods;
    public Score score;
    private Button reset;
    private Button close;
    
    Plane plane = new Plane(this);
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    
    private long enemyGenGap;
    private long bulletGenGap;
    private long painterGap;
    //private Timer genTimer;
    //private Timer bulletTimer;
    //private Timer paintTimer;

    Window(String title) {
        this.setTitle(title); // 視窗標題
        this.setSize(width, height); // 視窗大小
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
            System.exit(0);
            ///////////////////////
        });
        this.setVisible(true); // 顯示視窗
        //test
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        
        enemyGenGap = 0;

        try {
            while(true) {
                if(enemyGenGap % 10 == 0) {
                    Enemy stone = new Enemy(this);
                    enemies.add(stone);
                }

                Bullet newBullet = new Bullet(plane);
                bullets.add(newBullet);

                for(Enemy enemy : Window.this.enemies) {
                        enemy.moveEnemy();
                    }
                for(Bullet bullet : bullets) {
                    bullet.moveBullet();
                }
                repaint();
                determine(bullets, enemies, plane);

                enemyGenGap += 1;
                Thread.sleep(100);
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        /* genTimer = new Timer(6000, new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
            Enemy stone = new Enemy(Window.this);
            enemies.add(stone);
           } 
        });
        
        bulletTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bullet newBullet = new Bullet(plane);
                bullets.add(newBullet);
            }
        });
        
        paintTimer = new Timer(120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Enemy enemy : Window.this.enemies) {
                    enemy.moveEnemy();
                }
                for(Bullet bullet : bullets) {
                    bullet.moveBullet();
                }
                repaint();
                determine(bullets, enemies, plane);
            }
        });

        genTimer.start();
        bulletTimer.start();
        paintTimer.start(); */
    }

    // 處理鍵盤輸入
    public void keyTyped(KeyEvent e) {}
    // 裡面的有Temp的都是在測試
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 左
            keyReturn = -10;
            plane.movePlane();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 右
            keyReturn = 10;
            plane.movePlane();
        } else {
            keyReturn = 0;
        }
        
    }

    public void keyReleased(KeyEvent e) {
        keyReturn = 0;
    }

    public int returnKey() {
        return keyReturn;
    }

    // 所有會出現在螢幕上的物件都要draw坐在這邊，每個物件 ***一定要有實作draw()***
    public void paint(Graphics g) {
        super.paint(g); 
        Graphics2D g2d = (Graphics2D) g;
        for(Blood blood : bloods){
            blood.draw(g);
        }
        score.draw(g);
        reset.draw(g);
        close.draw(g);

        for(Enemy enemy : enemies) {
            enemy.paint(g2d);
        }
        for(Bullet bullet : bullets) {
            bullet.paint(g2d);
        }
        plane.paint(g2d);
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

 
    public void determine(List<Bullet> bullets, List<Enemy> enemies, Plane plane) {
        if (bullets == null || enemies == null) return;
    
        Iterator<Bullet> bulletIte = bullets.iterator();
        while (bulletIte.hasNext()) {
            Bullet bullet = bulletIte.next();
            boolean bulletRemoved = false;
    
            Iterator<Enemy> enemyIte = enemies.iterator();
            while (enemyIte.hasNext() && !bulletRemoved) {
                Enemy enemy = enemyIte.next();
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    enemy.enemyblood--;
                    score.scorePlus();
                    if (enemy.enemyblood == 0) {
                        enemyIte.remove();
                    }
                    bulletIte.remove();
                    bulletRemoved = true;
                }
            }
    
            if (!bulletRemoved && bullet.getY() == 0) {
                bulletIte.remove();
            }
        }
    
        Iterator<Enemy> enemyIte = enemies.iterator();
        while (enemyIte.hasNext()) {
            Enemy enemy = enemyIte.next();
            if (collision(plane.getBounds(), enemy.getBounds())) {
                bloodMinus();
                enemyIte.remove();
            } else if (enemy.getY() == height - enemy.enemySize) {
                enemyIte.remove();
                score.scoreMinus5();
            }
        }
    }

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