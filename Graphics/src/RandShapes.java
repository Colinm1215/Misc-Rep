//Code to start Monday Apr 9

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//HOPPS

public class RandShapes extends JPanel {

    //instance fields! Big variables visible everywhere in
//this class.
    private int mouseX, mouseY;
    private Color color;

    //Constructor for this class -> runs ONCE at the beginning
    public RandShapes(int width, int height) {
        setSize(width, height);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
//can't draw here.
//instead, get the info we need, and save it, so
//paintComponent can use that info.
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();

                Color newColor = new Color(19, 61, 249);
                color = newColor;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });
    }
    //this method does ALL the drawing in our program.
//Gets called sort of on a loop, but not really. It's weird.
    public void paintComponent(Graphics g) {
// super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillOval(mouseX-25, mouseY-25, 50, 50);
    }


    //sets ups the panel and frame. Probably not much to modify here.
    public static void main(String[] args) {
//The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Graphics!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

//This puts an object of this JPanel into the JFrame.
        RandShapes panel = new RandShapes(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
// window.setResizable(false);
    }

}