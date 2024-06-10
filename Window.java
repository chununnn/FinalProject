/*
 * Window(String 視窗標題,int 視窗長度,int 視窗寬度)
 */
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {

    Window(String title,int width,int height){
        this.setTitle(title); //視窗標題
        this.setVisible(true); // 顯示視窗
        this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 視窗的關閉按鈕、使用System exit方法退出應用程式
		this.setLocationRelativeTo(null); //讓視窗置中
    }
    
}

