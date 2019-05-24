import java.awt.*;
import java.util.ArrayList;


public class Projectile {

    int x, y;
    double vx, vy;
    int size;
    int age;
    private Color color;

    Projectile(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        size = 6;
        color = Color.BLACK;
    }

    void move(){
        x += vx;
        y += vy;
        age++;
    }

    public void move(Player player){
        x += vx;
        y += vy;
        age++;
    }

    public void move(Player player, ArrayList<Projectile> toAdd){
        x += vx;
        y += vy;
        age++;
    }

    public int getAge() {
        return age;
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillOval(x-size/2, y-size/2, size, size);
    }

    @SuppressWarnings("SameParameterValue")
    void setColor(Color color) {
        this.color = color;
    }

    public int getRadius(){
        return size/2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void setVx(double vx) {
        this.vx = vx;
    }

    void setVy(double vy) {
        this.vy = vy;
    }

    double getVx() {
        return vx;
    }

    double getVy() {
        return vy;
    }


}