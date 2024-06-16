import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;

public class Score extends JPanel{
    private int score;
    int x=950;
    int y=50;
    Score(int init_score){
        this.score = init_score;
    }
    
    public void resetScore(){
        this.score = 0;
    }

    public void scorePlus(){
        score += 1;
    }
    public void scoreMinus5(){
        if(!(score - 5 < 0))
        score -=5;
    }
    public int returnScore(){
        return score;
    }
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.ITALIC, 20));
        g.drawString("Score: " + score, x, y);
    }

    
}
