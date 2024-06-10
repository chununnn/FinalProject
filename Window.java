/*
 * Aiaplane 是我目前暫定玩家飛機的類別，之後等寫好之後我在改成寫好的 
 * Window(String 視窗標題,int 視窗長度,int 視窗寬度)
 * returnKey();   回傳鍵盤輸入 1是左鍵 2是右鍵 0是沒有輸入或是左右鍵之外的輸入
 */
import javax.swing.*;
import java.awt.event.*;
class Airplane extends JPanel{
    Window window;
    int keyReturn;
    Airplane(Window w){
        this.window = w;
    }
    public void KeyPressed(KeyEvent e) {  // 鍵盤按下時，移動
        if (e.getKeyCode() == KeyEvent.VK_LEFT){ // 往左移動
            keyReturn = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){ // 往右移動
            keyReturn = 2;
        }else{
            keyReturn = 0;
        }
    }

    public void KeyReleased(KeyEvent e) { // 鍵盤放開時，不移動
        keyReturn = 0;
    }
}
public class Window extends JFrame implements KeyListener{
    String key;
    Airplane airplane = new Airplane(this);
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
        airplane.KeyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
        airplane.KeyReleased(e);
    }
    public int retuenKey(){
        return this.airplane.keyReturn;
    }
    
}

