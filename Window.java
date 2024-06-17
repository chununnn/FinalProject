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
    private static final int enemiesMax = 15;
    private static final int bulletsMax = 25;
    boolean start = false;
    private int keyReturn;
    private Blood[] bloods;
    public Score score;
    private Button reset;
    private Button close;
    private boolean died = false;
    startLine startline = new startLine();
    Plane plane = new Plane(this);
    private Enemy[] enemies;
    private Bullet[] bullets;
    
    private long enemyGenGap;
    private long bulletGenGap;
    private long painterGap;

    Window(String title) {
        this.setTitle(title); // 視窗標題
        this.setSize(width, height); // 視窗大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(null); // 視窗居中
        this.addKeyListener(this);
        Background background = new Background();
        setContentPane(background);
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
            this.died = false;
        //////////////////////////////////////////////////////////////// 
        });
        close.setActionListener(() -> {
            //////////////////////// 這是按下close之後之行的
            dispose();
            System.exit(0);
            ///////////////////////
        });
        this.setVisible(true); // 顯示視窗
        
        enemyGenGap = 0;
        bulletGenGap = 0;
        int times = 0;
        try {
            while(true) {
                startline.changeColor(times++);
                this.enemies = new Enemy[enemiesMax];
                this.bullets = new Bullet[bulletsMax];
                int bulletIndex = 0;
                int enemyIndex = 0;
                long speedLevel = 0;
                if(!died){
                repaint();
                Thread.sleep(17);
                }
                while(start &&!died) {
                    double startTime = System.nanoTime();
                    
                    if(enemyGenGap % 150 == 0) {
                        Enemy stone = new Enemy(this, enemyIndex, (enemyGenGap / 2100) + 5);
                        enemies[enemyIndex % enemiesMax] = stone;
                        enemyIndex++;
                    }
                    if(bulletGenGap % 20 == 0) {
                        Bullet newBullet = new Bullet(plane, bulletIndex);
                        bullets[bulletIndex % bulletsMax] = newBullet;
                        bulletIndex++;
                    }
    
                    for(Enemy enemy : Window.this.enemies) {
                        if(enemy != null) {
                            enemy.moveEnemy();
                        }
                    }
                    for(Bullet bullet : bullets) {
                        if(bullet != null) {
                            bullet.moveBullet();
                        }
                    }
                    repaint();
                    determine(bullets, enemies, plane);
    
                    enemyGenGap++;
                    bulletGenGap ++;

                    double endTime = System.nanoTime();
                    System.out.println((endTime - startTime)*1000);
                    Thread.sleep(30);
                }
            }
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
        this.startline.setVisible(false);
        this.start = true;
        
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
        startline.draw(g);
        score.draw(g);
        reset.draw(g);
        close.draw(g);

        for(Enemy enemy : enemies) {
            if(enemy != null) {
                enemy.paint(g2d);
            }
        }
        for(Bullet bullet : bullets) {
            if(bullet != null) {
                bullet.paint(g2d);
            }
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
                
                this.died = true;
            }
    }

 
    public void determine(Bullet[] bullets, Enemy[] enemies, Plane plane) {
        if (bullets == null || enemies == null) return;
    
        for(Bullet bullet : bullets) {
            if(bullet != null) {
                for(Enemy enemy : enemies) {
                    if(enemy != null) {
                        if(bullet.getBounds().intersects(enemy.getBounds())) {
                            enemy.enemyblood--;
                            score.scorePlus();
                            bullets[bullet.getIndex() % bulletsMax] = null;
                            if (enemy.enemyblood == 0) {
                                enemies[enemy.getIndex() % enemiesMax] = null;
                            }
                        }
                    }
                }
            }
        }

        for(Enemy enemy : enemies) {
            if(enemy != null) {
                if (collision(plane.getBounds(), enemy.getBounds())) {
                    enemies[enemy.getIndex() % enemiesMax] = null;
                    bloodMinus();
                } else if (enemy.getY() == height - enemy.enemySize) {
                    enemies[enemy.getIndex() % enemiesMax] = null;
                    score.scoreMinus5();
                }
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