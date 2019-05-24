import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


class Player {

    private int x, y;
    private Image image;
    private final int w;
    private final int h;
    public int health;
    public int score;

    Player(int x, int y, File img) {
        score = 0;
        health = 100;
        this.x = x;
        this.y = y;


        if (img == null) {
            try {
                image = ImageIO.read(new File("VaultBoyFO3.png"));
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            try {
                image = ImageIO.read(img);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        w = 112;
        h = 114;
    }

    public void draw(Graphics2D g2, int width, int height){
        while (x <= 0) {
            x++;
        }
        while (x >= width) {
            x--;
        }while (y <= 0) {
            x++;
        }
        while (y >= height) {
            y--;
        }
        g2.drawImage(image, x, y, w, h,null);
    }

    public void move(boolean upPressed, boolean downPressed, boolean rightPressed, boolean leftPressed){
        double dx = 0, dy = 0;
        if (upPressed)
            dy = -1;
        if (downPressed)
            dy = 1;
        if (rightPressed)
            dx = 1;
        if (leftPressed)
            dx = -1;

        double speed = 10;
        if (dy != 0 && dx != 0) {
            dx *= 1 / Math.sqrt(2);
            dy *= 1 / Math.sqrt(2);
        }
        x += (dx * speed);
        y += (dy * speed);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkCollide(int cX, int cY) {

        return cX > x && cX < x + w && cY > y && cY < y + h;


    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}