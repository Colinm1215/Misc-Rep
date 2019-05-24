public class PulseBall extends Ball {
    boolean isGrowing = true;
    int minDiameter;
    int maxDiameter;

    PulseBall(int x, int y, int diameter) {
        super(x, y, diameter);
        minDiameter = diameter / 2;
        maxDiameter =  diameter * 2;
    }

    @Override
    public void move() {
        x += vx;
        y += vy;
        checkWallCollide();

        if (isGrowing) {
            diameter += 1;
        } else {
            diameter -= 1;
        }

        if (diameter >= maxDiameter) {
            isGrowing = false;
        } else if (diameter <= minDiameter) {
            isGrowing = true;
        }
//        return false;
    }
}
