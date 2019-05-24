import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {
    public int id;
    private Color defColor;
    private Color lightColor;

    Button(int id, Color color, int x, int y, int w, int h) {
        this.id = id;
        this.setBackground(color);
        this.setOpaque(true);
        this.setBorderPainted(false);
        defColor = color;
        lightColor = defColor.brighter().brighter();
        this.setBounds(x, y, w, h);
    }

    public void changeColor(int x) {
        if (x == 1) {
            this.setBackground(lightColor);
        } else if (x == 2){
            this.setBackground(defColor);
        }
    }

}
