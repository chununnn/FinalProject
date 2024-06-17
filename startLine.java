//setVIsible是設定可不可見
//startline.changeColor(times++); 這是我測試用的寫法 供參 time 是刷新次數
//在window.java的draw記得新增startline.draw(g);


import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

public class startLine {
    boolean visible = true;
    int x=375;
    int y=500;
    Color color  = new Color(255,255,0);  //default gray 
    private final Color[] yellowToBrownGradient = {
        new Color(255, 255, 102),   // 黃色
        new Color(255, 229, 102),   // 中間色1
        new Color(255, 204, 102),   // 中間色2
        new Color(255, 178, 102),   // 中間色3
        new Color(255, 153, 102),   // 中間色4
        new Color(255, 128, 102),   // 中間色5
        new Color(255, 102, 102),   // 中間色6
        new Color(204, 102, 102),   // 中間色7
        new Color(153, 102, 102),   // 中間色8
        new Color(102, 102, 102),   // 中間色9
        new Color(102, 76, 76),     // 中間色10
        new Color(102, 51, 0),      // 咖啡色
        new Color(102, 76, 76),     // 中間色10
        new Color(102, 102, 102),   // 中間色9
        new Color(153, 102, 102),   // 中間色8
        new Color(204, 102, 102),   // 中間色7
        new Color(255, 102, 102),   // 中間色6
        new Color(255, 128, 102),   // 中間色5
        new Color(255, 153, 102),   // 中間色4
        new Color(255, 178, 102),   // 中間色3
        new Color(255, 204, 102),   // 中間色2
        new Color(255, 229, 102),   // 中間色1
       
    };
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public void changeColor(int time){
        int i = time/2;
        int j = i%21;
        color = yellowToBrownGradient[j];
    }
    
    public void draw(Graphics g) {
        if(!visible){return ;}
        g.setColor(color);
        g.setFont(new Font("Arial", Font.ITALIC, 30));
        g.drawString("Press Any Key To Start",x,y);
    }

    
}


