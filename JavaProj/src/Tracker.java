import java.awt.*;


public class Tracker extends Projectile{

    Tracker(int x, int y, int vx, int vy) {
        super(x, y, vx, vy);
        setColor(Color.RED);
    }

    public void move(Player player){
        int dx = (player.getX() + (player.getW()/2)) - getX();
        int dy = (player.getY() + (player.getH()/2)) - getY();
        double bottom = Math.sqrt(dx * dx + dy * dy);
        double dirX = dx / bottom;
        double dirY = dy / bottom;

        double newVx = getVx() + dirX;
        double newVy = getVy() + dirY;

        double mag = Math.sqrt(newVx*newVx + newVy*newVy);
        double speedLimit = 5;  //20 for German's trick
        if (mag > speedLimit){
            newVx = newVx/mag * speedLimit;
            newVy = newVy/mag * speedLimit;
        }
        setVx(newVx);
        setVy(newVy);
        super.move();
    }

}