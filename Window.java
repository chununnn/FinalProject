/*
 * keyReturn代表使用者輸入 1是左鍵 2是右鍵 0是其他或是無輸入
 * returnKey()回傳輸入
 * bloodMinus()會執行bloodMinusOne()並且顯限於螢幕上，讀去血量是以第一個blood為基準 
 */



import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;

public class Window extends JFrame implements KeyListener {
    private int keyReturn;
    private Blood[] bloods;
    private Score score;
    Window(String title, int width, int height) {
        this.setTitle(title); // 窗口标题
        this.setSize(width, height); // 窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗口关闭操作
        this.setLocationRelativeTo(null); // 窗口居中
        this.addKeyListener(this);
        bloods =new Blood[]{new Blood(20),
                 new Blood(50),
                 new Blood(80)};
        score = new Score(0);
        
        
        this.setVisible(true); // 显示窗口
    }

    // 按键输入处理
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 左键
            keyReturn = 1;            
            System.out.println(1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 右键
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

    // 绘制血量对象
    public void paint(Graphics g) {
        super.paint(g); // 调用父类的 paint 方法
        for(Blood blood : bloods){
            blood.draw(g);
        }
        score.draw(g);
    }

    public void bloodMinus(){
            bloods[0].bloodMinusOne();
            int index = bloods[0].getBlood();  //以第一個blood為基準
            if(index == -1){
                //血量已經歸零
            }else{bloods[index].eliminate();}
    }
    public void scorePlus(){
        score.scorePlus();
    }
    public static void main(String[] args) {
        new Window("test",1080,720);
        
    }
    
}




            
