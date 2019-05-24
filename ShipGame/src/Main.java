import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings({"unused", "FieldCanBeLocal", "CanBeFinal", "SpellCheckingInspection", "WeakerAccess"})
class Main extends JPanel {
    private final Color Black = new Color(0, 0, 0);
    private final Color White = new Color(255, 255, 255);
    private final Color Red = new Color(255, 0, 0);
    private final Color Green = new Color(0, 255, 0);
    private final Color Blue = new Color(0, 0, 255);
    private final Color Flame = new Color(255, 120, 30);
    private final int mouseY = 525;
    int eneKill = 0;
    ArrayList<int[]> Lasers = new ArrayList<>();
    ArrayList<int[]> eneLasers = new ArrayList<>();
    ArrayList<Enemy> Ene = new ArrayList<>();
    private int mouseX = 210;
    static JFrame window = new JFrame("Ship Game");
    private final KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                int[] Pos = {mouseX + 45, mouseY - 30};
                Lasers.add(Pos);
            }

        }
    };
    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int[] Pos = {mouseX + 45, mouseY - 30};
            Lasers.add(Pos);
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
    };
    private final MouseMotionListener motionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            while (mouseX < 0) {
                mouseX += 1;
            }
            while (mouseX + 100 > getWidth()) {
                mouseX -= 1;
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            while (mouseX < 0) {
                mouseX += 1;
            }
            while (mouseX + 100 > getWidth()) {
                mouseX -= 1;
            }
            repaint();

        }
    };

    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        Main panel = new Main(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }


    public Main(int width, int height) {
        setSize(width, height);
        addMouseMotionListener(motionListener);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        for (int x = 50; x <= 525; x += 150) {
            for (int y = 75; y <= 375; y += 150) {
                int[] e = {x, y};
                Ene.add(new Enemy(e));
            }
        }
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Black);
        if (Ene.size() > 0) {
            for (Enemy e : Ene) {
                e.draw(g2);
                int[] las = e.fire();
                if (las != null) {
                    if (eneLasers.size() <= 2) {
                        eneLasers.add(las);
                    }
                }
            }
            String str = String.format("Enemies Killed : %d", eneKill);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2.drawString(str, 25, 50);
            g2.fillRect(mouseX, mouseY, 100, 20);
            g2.fillRect(mouseX + 45, mouseY - 30, 10, 30);
            g2.fillRect(mouseX + 20, mouseY - 10, 60, 30);
            g2.fillRect(mouseX + 60, mouseY, 20, 30);
            g2.fillRect(mouseX + 20, mouseY, 20, 30);
            g2.setColor(Flame);
            g2.fillRect(mouseX + 60, mouseY + 30, 20, 10);
            g2.fillRect(mouseX + 20, mouseY + 30, 20, 10);
            g2.setColor(Red);
            ArrayList<Integer> eRem = new ArrayList<>();
            ArrayList<Integer> lRem = new ArrayList<>();
            ArrayList<Integer> eLRem = new ArrayList<>();
            // Lasers

            // Enemy Lasers
            for (int[] las : eneLasers) {
                g2.fillRect(las[0], las[1], 5, 10);
                if (las[0]+5 >= mouseX && las[0] <=  mouseX+80) {
                    if(las[1]+10 >= mouseY-30 && las[1] <= mouseY+40) {
                        super.paintComponent(g2);
                        removeKeyListener(keyListener);
                        removeMouseListener(mouseListener);
                        removeMouseMotionListener(motionListener);
                        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                        g2.drawString(str, 25, 50);
                        String win = "You died!!!";
                        g2.drawString(win, 150, 250);
                    }
                } else if (las[1] >= getHeight()) {
                    eLRem.add(eneLasers.indexOf(las));
                }
            }

            for (int[] las : eneLasers) {
                try {
                    las[1] += 1;
                } catch (NullPointerException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }

            // Player Lasers
            for (int[] i : Lasers) {
                g2.fillRect(i[0], i[1], 5, 10);
                for (Enemy e : Ene) {
                    if (i[0]+ 5 >= e.coords[0] && i[0] <= e.coords[0] + 50) {
                        if (i[1]+10 >= e.coords[1] && i[1] <= e.coords[1] + 25) {
                            if (!eRem.contains(Ene.indexOf(e))) {
                                eRem.add(Ene.indexOf(e));
                                eneKill += 1;
                            }
                            if (!lRem.contains(Lasers.indexOf(i))) {
                                lRem.add(Lasers.indexOf(i));
                            }
                            break;
                        }
                    } else if (i[1] <= 0) {
                        lRem.add(Lasers.indexOf(i));
                    }
                }
            }
            for (int[] i : Lasers) {
                try {
                    i[1] -= 1;
                } catch (NullPointerException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }


            for (int n : eRem) {
                try {
                    Ene.remove(n);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                }
            }
            for (int n : lRem) {
                try {
                Lasers.remove(n);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                }
            }
            for (int n : eLRem) {
                try {
                eneLasers.remove(n);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                }
            }


            repaint();
        } else {
            removeKeyListener(keyListener);
            removeMouseListener(mouseListener);
            removeMouseMotionListener(motionListener);
            String str = String.format("Enemies Killed : %d", eneKill);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2.drawString(str, 25, 50);
            String win = "You Won!!!";
            g2.drawString(win, 150, 250);
        }
    }
}
