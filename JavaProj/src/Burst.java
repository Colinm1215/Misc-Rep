import java.util.ArrayList;


class Burst extends Projectile {

    Burst(int x, int y, int vx, int vy, int rad) {
        super(x, y, vx, vy);
        size = rad;
    }

    @Override
    public void move(Player player, ArrayList<Projectile> toAdd){
        double dist = Math.sqrt((player.getX() - x)^2 + (player.getY() - y)^2);
        if (dist <= 6 && size > 5) {
            vx = 0;
            vy = 0;
            size /= 2;
            toAdd.add(new Burst(x, y, 0, 1, size));
            toAdd.add(new Burst(x, y, 0, -1, size));
            toAdd.add(new Burst(x, y, 1, 0, size));
            toAdd.add(new Burst(x, y, -1, 0, size));
            toAdd.add(new Burst(x, y, 1, 1, size));
            toAdd.add(new Burst(x, y, 1, -1, size));
            toAdd.add(new Burst(x, y, -1, 1, size));
            toAdd.add(new Burst(x, y, -1, -1, size));
            toAdd.add(new Burst(x, y, -1, -1, size));
            age = 60 * 4;
        } else {
            x += vx;
            y += vy;
            age++;
        }
    }
}
