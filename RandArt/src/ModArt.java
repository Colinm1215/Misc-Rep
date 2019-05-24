import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class ModArt extends JPanel {

    private final ArrayList<Diamond> diamonds = new ArrayList<>();
    private boolean Luci = true;
    private final Luci luci;

    //Constructor for this class -> runs ONCE at the beginning
    private ModArt() {
        setSize(600, 600);
        luci = new Luci(getWidth(), getHeight());
        setDiamonds();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDiamonds();
                int rand = (int)(Math.random() * 100);
                Luci = rand <= 10;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void setDiamonds() {
        diamonds.clear();
        for (int x = 0; x <= getWidth(); x += 50) {
            for (int y = 0; y <= getHeight(); y += 50) {
                diamonds.add(new Diamond(x, y, 50));
                diamonds.add(new Diamond(x+25, y+25, 50));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Diamond d : diamonds) {
            if (!Luci) {
                d.draw(g2, null);
            } else {
                luci.drawS(d, g2);
            }
        }
        repaint();
    }


    //sets ups the panel and frame. Probably not much to modify here.
    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Graphics Window");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        ModArt panel = new ModArt();

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }

}