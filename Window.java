/*
 *  
 * Window(String 視窗標題,int 視窗長度,int 視窗寬度)
 * returnKey();   回傳鍵盤輸入 1是左鍵 2是右鍵 0是沒有輸入或是左右鍵之外的輸入
 */
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame implements KeyListener{
    private int keyReturn;
    
    Window(String title,int width,int height){
        this.setTitle(title); //視窗標題
        this.setVisible(true); // 顯示視窗
        this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 視窗的關閉按鈕、使用System exit方法退出應用程式
		this.setLocationRelativeTo(null); //讓視窗置中
        this.addKeyListener(this);
        
    }
    public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){ // 往左移動
            keyReturn = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){ // 往右移動
            keyReturn = 2;
        }else{
            keyReturn = 0;
        }
    }
    public void keyReleased(KeyEvent e) {
        keyReturn = 0;
    }
    public int returnKey(){
        return keyReturn;
    }
    
}

