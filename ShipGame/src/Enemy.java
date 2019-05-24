import java.awt.*;
import java.util.Random;

class Enemy {
    public final int[] coords;

    public Enemy(int[] xy) {
        coords = xy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(coords[0], coords[1], 50, 25);
        g.fillRect(coords[0]+20, coords[1], 10, 35);
    }

    public int[] fire() {
        if (new Random().nextInt(1000) == 0) {
            return (new int[] {coords[0]+23, coords[1]+35});
        } else {
            return null;
        }
    }
}
