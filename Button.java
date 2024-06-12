/*
 * 
 * 
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements MouseListener {
    private String label;
    private int x;
    int y = 320;
    int width = 80;
    int height = 45;
    private Color color = Color.LIGHT_GRAY;
    private boolean hovered;
    private boolean visible;
    private ActionListener actionListener;

    public Button(String label, int x) {
        this.label = label;
        this.x = x;
        
        this.hovered = false;
        this.visible = false; // default不可見
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void draw(Graphics g) {
        if (!visible) return; // 如果visible==false  不會顯現
        g.setColor(hovered ? color.darker() : color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(label, x + 10, y + 30);
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!visible) return; // visible == false的時候不處理
        if (isInside(e.getX(), e.getY()) && actionListener != null) {
            actionListener.actionPerformed();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!visible) return; // visible == false的時候不處理
        if (isInside(e.getX(), e.getY())) {
            hovered = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!visible) return; // visible == false的時候不處理
        hovered = false;
    }

    private boolean isInside(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public interface ActionListener {
        void actionPerformed();
    }
}
