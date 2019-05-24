import java.awt.*;

public class Ball {
    int x, y;
    int vx, vy;
    int diameter;

    Ball(int x, int y, int diameter) {
        vx = 2;
        vy = -2;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillOval(x, y, diameter, diameter);
    }

    public void move() {
        x += vx;
        y += vy;
        checkWallCollide();
    }

    void checkWallCollide() {
        if (x+diameter >= 800 || x <= 0) {
            x -= vx;
            vx *= -1;
        }
        if (y+diameter >= 800 || y <= 0) {
            y -= vy;
            vy *= -1;
        }
    }

    public void checkCollisions(Ball ball) {
        Point thisCenter = getCenter();
        Point otherCenter = ball.getCenter();
        int dx = otherCenter.x - thisCenter.x;
        int dy = otherCenter.y - thisCenter.y;
        double distance = dx*dx + dy*dy;
        distance = Math.sqrt(distance);
        if (distance <= getDiameter()/2 + ball.getDiameter()/2) {
            while (distance < getDiameter()) {
                ball.setX(ball.getX()-ball.getVx());
                ball.setY(ball.getY()-ball.getVy());
                thisCenter = getCenter();
                otherCenter = ball.getCenter();
                dx = otherCenter.x - thisCenter.x;
                dy = otherCenter.y - thisCenter.y;
                distance = dx*dx + dy*dy;
                distance = Math.sqrt(distance);
            }
            int thisMass = getDiameter();
            int otherMass = ball.getDiameter();

            int newVelX1 = (vx * (thisMass - otherMass) +
                    (2 * otherMass * ball.getVx()) / (thisMass + otherMass));
            int newVelY1 = (vy * (thisMass - otherMass) +
                    (2 * otherMass * ball.getVy()) / (thisMass + otherMass));

            int newVelX2 = (ball.getVx() * (otherMass - thisMass) +
                    (2 * thisMass * vx) / (otherMass + thisMass));
            int newVelY2 = (ball.getVy() * (otherMass - thisMass) +
                    (2 * thisMass * vy) / (otherMass + thisMass));

            vx = newVelX1;
            vy = newVelY1;

            ball.setVx(newVelX2);
            ball.setVy(newVelY2);
        }
    }

    public Point getCenter(){
        Point center = new Point();
        center.x = x+(diameter/2);
        center.y = y+(diameter/2);
        return center;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
}
