import java.awt.*;


class TrackerTurret extends Enemy {

    TrackerTurret(int x, int y) {
        super(x, y);
        setColor(new Color(210, 0, 255));
    }

    @Override
    public Projectile spawnProjectile(int vx, int vy) {
        return new Tracker(getX(), getY(), vx, vy);
    }
}