/*
 * bloodMinusOne() 會把血量-1並看血量是否歸零
 * eliminate()會把球改成灰色
 */


import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

class Blood {
    private int blood;
    private Color color;
    private int x;
    private int y = 50;
    private int diameter = 30;

    // 构造函数初始化血量和x坐标
    Blood(int x) {
        this.blood = 3;
        this.x = x;
        this.color = Color.RED;
    }

    // 血量减少并检查是否死亡
    public void bloodMinusOne() {
        this.blood -= 1;
        if (this.blood == 0) {
            System.out.println("died");
        }
        
    }

    // 获取当前血量
    public int getBlood() {
        return this.blood;
    }

    // 绘制血量对象
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    // 更新颜色基于当前血量
    public void eliminate() {
        this.color = Color.GRAY;
    }
}