//setVIsible是設定可不可見
//startline.changeColor(times++); 這是我測試用的寫法 供參 time 是刷新次數
//在window.java的draw記得新增startline.draw(g);


import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

public class StartLine {
    boolean visible = true;
    int x=375;
    int y=500;
    Color color  = new Color(255,255,0);  //default gray 
    private final Color[] whiteToBlackToWhiteGradient = {
        new Color(255, 255, 255), // 白色
        new Color(242, 242, 242), // 中間色1
        new Color(229, 229, 229), // 中間色2
        new Color(217, 217, 217), // 中間色3
        new Color(204, 204, 204), // 中間色4
        new Color(191, 191, 191), // 中間色5
        new Color(179, 179, 179), // 中間色6
        new Color(166, 166, 166), // 中間色7
        new Color(153, 153, 153), // 中間色8
        new Color(140, 140, 140), // 中間色9
        new Color(128, 128, 128), // 中間色10
        new Color(115, 115, 115), // 中間色11
        new Color(102, 102, 102), // 中間色12
        new Color(89, 89, 89),    // 中間色13
        new Color(77, 77, 77),    // 中間色14
        new Color(64, 64, 64),    // 中間色15
        new Color(51, 51, 51),    // 中間色16
        new Color(38, 38, 38),    // 中間色17
        new Color(26, 26, 26),    // 中間色18
        new Color(13, 13, 13),    // 中間色19
        new Color(0, 0, 0),       // 黑色
        new Color(13, 13, 13),    // 中間色19
        new Color(26, 26, 26),    // 中間色18
        new Color(38, 38, 38),    // 中間色17
        new Color(51, 51, 51),    // 中間色16
        new Color(64, 64, 64),    // 中間色15
        new Color(77, 77, 77),    // 中間色14
        new Color(89, 89, 89),    // 中間色13
        new Color(102, 102, 102), // 中間色12
        new Color(115, 115, 115), // 中間色11
        new Color(128, 128, 128), // 中間色10
        new Color(140, 140, 140), // 中間色9
        new Color(153, 153, 153), // 中間色8
        new Color(166, 166, 166), // 中間色7
        new Color(179, 179, 179), // 中間色6
        new Color(191, 191, 191), // 中間色5
        new Color(204, 204, 204), // 中間色4
        new Color(217, 217, 217), // 中間色3
        new Color(229, 229, 229), // 中間色2
        new Color(242, 242, 242)  // 中間色1
        
    };
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public void changeColor(int time){
        int i = time/2;
        int j = i%whiteToBlackToWhiteGradient.length;
        color = whiteToBlackToWhiteGradient[j];
    }
    
    public void draw(Graphics g) {
        if(!visible){return ;}
        g.setColor(color);
        g.setFont(new Font("Arial", Font.ITALIC, 30));
        g.drawString("Press Any Key To Start",x,y);
    }

    
}

