public class RandomWalkBall extends Ball{
    char[] dirPossible = new char[]{'N','E','S','W'};
    char dir;

    RandomWalkBall(int x, int y, int diameter) {
        super(x, y, diameter);
    }

    @Override
    public void move() {
        switch (dir) {
            case 'N': // subtract from y
                y -= vy;
                break;
            case 'E': // add to x
                x += vx;
                break;
            case 'S': // add to y
                y += vy;
                break;
            case 'W': // subtract from x
                x -= vx;
                break;
            default:
                break;
        }
        checkWallCollide();
    }

    public void changeDir() {
        int newDir = (int)(Math.random() * 4);
        dir = dirPossible[newDir];
    }
}
