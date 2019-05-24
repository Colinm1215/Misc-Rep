import javax.swing.*;
import java.awt.*;

//TODO Put your name here.

public class ThirdGraphics extends JPanel {



    public ThirdGraphics(int width, int height) {
        setSize(width, height);


    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN.darker());
        g2.fillOval(75, 75, 270, 250);
    }


    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Graphics!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        ThirdGraphics panel = new ThirdGraphics(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
//        window.setResizable(false);
    }

}