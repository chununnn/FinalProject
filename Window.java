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
import java.awt.event.*;

public class Window extends JFrame implements KeyListener {
    private int keyReturn;
    private Blood[] bloods;
    private Score score;
    private Button reset;
    private Button close;
    Plane plane = new Plane(this);
    Enemy enemy = new Enemy(this);

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
    }

    // 處理鍵盤輸入
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 左
            keyReturn = 1;            
            System.out.println(1);
            bloodMinus();
            repaint();
            
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 右
            keyReturn = 2;
            System.out.println(2);
        } else {
            keyReturn = 0;
            System.out.println(0);
        }
        
    }

    public void keyReleased(KeyEvent e) {
        keyReturn = 0;
        System.out.println(0);
        
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

        plane.paint(g2d);
        enemy.paint(g2d);
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
    
    public static void main(String[] args) {
        new Window("test");
        
    }
    
}




            
