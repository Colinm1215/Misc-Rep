import java.awt.*;

public class Spiral {
    int x, y, width, height, startAngle, arcAngle;

    public Spiral(int x, int y, int width, int height, int startAngle, int arcAngle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    public void draw(Graphics2D g2) {
        g2.fillArc(x, y, width, height, startAngle, arcAngle);
    }
}
