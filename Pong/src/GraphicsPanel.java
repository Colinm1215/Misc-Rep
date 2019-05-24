import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class GraphicsPanel extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    int count = 0;
    private Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            count++;
            for (Ball ball : balls) {
                if (ball instanceof RandomWalkBall && count % 10 == 0) {
                    ((RandomWalkBall) ball).changeDir();
                }
            }
            repaint();
        }
    });

    GraphicsPanel(int width, int height) {
        timer.start();
        setSize(width, height);
        balls.add(new RandomWalkBall(0, 0, 20));
        for (int i  = 0;  i < 20; i++) {
            balls.add(new Ball((int) (Math.random()*getWidth()), (int) (Math.random()*getHeight()), 30));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball ball :  balls) {
            ball.draw(g2);
            ball.move();
            for (Ball otherBall : balls) {
                if (otherBall != ball) {
                    ball.checkCollisions(otherBall);
                }
            }
        }
    }

}