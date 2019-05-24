import java.awt.*;

class BurstTurret extends Enemy{
    BurstTurret(int x, int y) {
        super(x, y);
        setColor(new Color(255, 0, 0));
    }

    @Override
    public Projectile spawnProjectile(int vx, int vy) {
        return new Burst(getX(), getY(), vx, vy, 16);
    }
}
