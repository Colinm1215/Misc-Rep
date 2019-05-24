import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
class CircSpiral extends JPanel {
    private static int Delta;
    private BufferedImage image;
    private final ArrayList<Color> colors = new ArrayList<>();
    ArrayList<Ellipse2D> circles = new ArrayList<>();

    private CircSpiral(int h, int w) {
        setSize(h, w);

        for (int r=0; r<100; r++) colors.add(new Color(r*255/100,       255,         0));
        for (int g=100; g>0; g--) colors.add(new Color(      255, g*255/100,         0));
        for (int b=0; b<25; b++) colors.add(new Color(      255,         0, b*255/100));
        for (int r=100; r>0; r--) colors.add(new Color(r*255/100,         0,       255));
        for (int g=0; g<25; g++) colors.add(new Color(        0, g*255/100,       255));
        for (int b=100; b>0; b--) colors.add(new Color(        0,       255, b*255/100));


        Delta = getWidth()/colors.size();

        try {
            image = ImageIO.read(new File("Kermit.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
        Delta = getWidth()/colors.size();

        int x = getWidth()/2 - Delta;
        int y = getHeight()/2 - Delta;
        int width = Delta*2;
        int height = Delta*2;

        for (Color c : colors) {
            width += Delta*2;
            height += Delta*2;
            x -= Delta;
            y -= Delta;
        }

        for (Color c : colors) {
            g2.setColor(c);
            g2.fillOval(x, y, width, height);
            width -= Delta*2;
            height -= Delta*2;
            x += Delta;
            y += Delta;
        }


        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2.drawImage(image, getWidth()/2 - 320, getHeight()/2 - 250, this);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        Color co = colors.get(0);
        colors.add(colors.size()-1, co);
        colors.remove(0);
        repaint();
    }

    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Psychedelic Art - Colin Mettler");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        CircSpiral panel = new CircSpiral(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}