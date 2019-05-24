import javax.swing.*;
import java.awt.*;

public class RandomDots extends JPanel {

    private RandomDots(int w, int h) {
        setSize(w, h);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int cx = getWidth()/2;
        int cy = getHeight()/2;

        for (int i = 0; i < 10000; i++) {
            double dx = Math.random() - .5;
            double dy = Math.random() - .5;

            double distance = Math.sqrt(dx*dx + dy*dy);
            double length = Math.random()*255;
            dx = dx / distance * length;
            dy = dy / distance * length;

            float h = (float)(Math.atan(dy/dx)/Math.PI);
            float s = 1;
            Color myColor = Color.getHSBColor(h, 1, 1);
            g2.setColor(myColor);
            g2.fillOval((int)dx + cx,(int)dy + cy, 4, 4);
        }
    }

    //sets ups the panel and frame. Probably not much to modify here.
    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Graphics Window");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        RandomDots panel = new RandomDots(600,600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }

}
