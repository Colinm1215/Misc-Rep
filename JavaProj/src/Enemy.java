import java.awt.*;
import java.util.ArrayList;


class Enemy {

    private final int x;
    private final int y;
    private final ArrayList<Projectile> shots;
    private int shotTimer;
    private final int shotDelay;
    private final int radius;
    private Color color;

    Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        shotDelay = 60;
        shotTimer = 0;
        shots = new ArrayList<>();
        radius = 25;
        color = Color.BLACK;
    }

    public boolean intersects(Projectile proj){
        double dx = x - proj.getX();
        double dy = y - proj.getY();
        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance < radius + proj.getRadius();
    }

    public void shoot(Player player, ArrayList<Projectile> projectiles){
        shotTimer++;
        if(shotTimer >= shotDelay) {
            shotTimer = 0;
            int dx = (player.getX() + (player.getW()/2))  - x;
            int dy = (player.getY() + (player.getH()/2)) - y;
            double bottom = Math.sqrt(dx * dx + dy * dy);
            double dirX = dx / bottom;
            double dirY = dy / bottom;

            int vx = (int) (dirX * 10);
            int vy = (int) (dirY * 10);

            Projectile shot = spawnProjectile(vx, vy);
            projectiles.add(shot);
        }
    }

    Projectile spawnProjectile(int vx, int vy){
        return new Projectile(x, y, vx, vy);
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fillOval(x-radius, y-radius, 2*radius, 2*radius);
    }

    @SuppressWarnings("unused")
    public ArrayList<Projectile> getShots() {
        return shots;
    }

    void setColor(Color color) {
        this.color = color;
    }
}