import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Random;

public class Game extends JFrame{

    ArrayList<Button> Buttons = new ArrayList<Button>(); // holds buttons
    ArrayList<Integer> Pattern = new ArrayList<Integer>(); // Pattern of Colors
    ArrayList<Integer> clickedPattern = new ArrayList<Integer>(); // Colors player clicked
    Random rand = new Random();
    int count = 0;
    JLabel score;

    Game(){ // create Frame
        final JFrame frame = this;
        this.addWindowListener(new java.awt.event.WindowAdapter() { // exit logic
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog( frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // size, setvisible, etc
        setSize(600,750);
        setLayout(null);
        setVisible(true);
        score = new JLabel(String.format("Score : %d", Pattern.size()));
        this.add(score);
        score.setBounds(50, 20, 100, 100);
        createButtons();
        if (JOptionPane.showConfirmDialog(this,
                String.format("Ready to Play?"), "Simon Says",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            Pattern.clear();
            clickedPattern.clear();
            play();
        } else {
            System.exit(0);
        }
    }

    private void drawPattern() {
        int nextColor = rand.nextInt(5);
        for (Button btn : Buttons) {
            if (btn.id == nextColor) {
                Pattern.add(nextColor);
            }
        }
        if (Pattern.size() > 0) {
            for (int i : Pattern) {
                for (final Button btn :  Buttons) {
                    if (btn.id == i) {
                        count = 0;
                        final Timer timer = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                count++;
                                if (count == 1) btn.changeColor(1);
                                if (count == 2) btn.changeColor(2);
                                if (count == 3) ((Timer) e.getSource()).stop();
                            }
                        });
                        timer.start();
                        while (timer.isRunning()){
                        }
                    }
                }
            }
        }
        for (Button btn : Buttons) {
            btn.setEnabled(true);
        }
    }

    private int checkPattern() {
        for (Button btn : Buttons) {
            btn.setEnabled(false);
        }

        for (int y = 0; y <= Pattern.size() - 1; y++) {
            try {
                if (Pattern.get(y) != clickedPattern.get(y)) {
                    return 0;
                }
            } catch (IndexOutOfBoundsException e) {
                return 1;
            }
        }
        return 2;
    }

    private void play() {
        clickedPattern.clear();
        int x = 1;
        boolean doPattern = true;
        while (x == 1) {
            score.setText(String.format("Score : %d", ((Pattern.size()-1 >= 1) ? Pattern.size()-1 : 0)));
            if (doPattern) {
                drawPattern();
                doPattern = false;
            } else {
                int cp = checkPattern();
                System.out.println("cp : " + cp);
                if (cp == 1) {
                    score.setText(String.format("Score : %d", ((Pattern.size()-1 >= 1) ? Pattern.size()-1 : 0)));
                    play();
                }
                else if (cp == 0){
                    break;
                } else if (cp == 2) {
                    // do nothing
                }
            }
        }

        int jp = JOptionPane.showConfirmDialog(this,
                String.format("You Lost!! Your score was %d. Do you want to play again?", Pattern.size()-1), "You Lost",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        System.out.println("JP : " + jp);

        if ( jp == JOptionPane.YES_OPTION) {
            Pattern.clear();
            clickedPattern.clear();
            play();
        } else if (jp == JOptionPane.NO_OPTION) {
            System.exit(0);
        }

    }

    private void mouseRelease(Button btn) {
        if (btn.isEnabled()) {
            btn.changeColor(2);
            clickedPattern.add(btn.id);
        }
    }

    private void mousePress(Button btn) {
        if (btn.isEnabled()) btn.changeColor(1);
    }

    private void createButtons(){
        final Button rBtn = new Button(0, Color.RED.darker().darker(), 65, 200, 230, 230 ); // red button - top left
        rBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePress(rBtn);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                mouseRelease(rBtn);
            }
        });
        Buttons.add(rBtn);
        this.add(rBtn);


        final Button bBtn = new Button(1, Color.BLUE.darker().darker(), 305, 200, 230, 230 ); // red button - top left
        bBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePress(bBtn);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                mouseRelease(bBtn);
            }
        });
        Buttons.add(bBtn);
        this.add(bBtn);


        final Button gBtn = new Button(2, Color.GREEN.darker().darker(), 65, 440, 230, 230 ); // red button - top left
        gBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePress(gBtn);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                mouseRelease(gBtn);
            }
        });
        Buttons.add(gBtn);
        this.add(gBtn);


        final Button yBtn = new Button(3, Color.YELLOW.darker().darker(), 305, 440, 230, 230 ); // red button - top left
        yBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePress(yBtn);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                mouseRelease(yBtn);
            }
        });
        Buttons.add(yBtn);
        this.add(yBtn);

        for (Button b : Buttons) {
            b.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new Game();
    }

}
