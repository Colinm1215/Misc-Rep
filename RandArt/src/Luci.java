import java.awt.*;

class Luci {
    private final Polygon face;
    private final Polygon lEye;
    private final Polygon rEye;
    private final Polygon mouth;
    private final Polygon rHorn;
    private final Polygon lHorn;
    private final Polygon rBrow;
    private final Polygon lBrow;

    @SuppressWarnings("WeakerAccess")
    public Luci(int w, int h) {

        int[] xs = {w/2, w/2 + 200, w/2, w/2 - 200};
        int[] ys = {h/2 - 200, h/2, h/2 + 200, h/2};
        face = new Polygon(xs, ys, 4);

        xs = new int[]{w/2 + 20, w/2 + 50, w/2 + 25, w/2};
        ys = new int[]{h/2, h/2 - 25, h/2 - 50, h/2 - 25};
        rEye = new Polygon(xs, ys, 4);

        xs = new int[]{w/2 - 75, w/2 - 100, w/2 - 75, w/2 - 50};
        ys = new int[]{h/2 - 50, h/2 - 30, h/2, h/2 - 30};
        lEye = new Polygon(xs, ys, 4);

        xs = new int[]{w/2 - 50, w/2 - 30, w/2, w/2 + 20, w/2 - 30, w/2 - 75};
        ys = new int[]{h/2 + 75, h/2 + 50, h/2 + 70, h/2 + 50, h/2, h/2 + 50};
        mouth = new Polygon(xs, ys, 6);

        xs = new int[]{w/2 - 130, w/2 - 150, w/2 - 125, w/2 - 150, w/2 - 125, w/2 - 150, w/2 - 125, w/2 - 150,
                w/2 - 175, w/2 - 200, w/2 - 175, w/2 - 200, w/2 - 175, w/2 - 200, w/2 - 150};
        ys = new int[]{h/2 - 100, h/2 - 120, h/2 - 150, h/2 - 170, h/2 -200, h/2 - 220, h/2 - 250, h/2 - 270,
                h/2 - 250, h/2 - 220, h/2 - 200, h/2 - 170, h/2 - 150, h/2 - 120, h/2 - 75};
        rHorn = new Polygon(xs, ys, 15);

        xs = new int[]{w/2 + 80, w/2 + 100, w/2 + 75, w/2 + 100, w/2 + 75, w/2 + 100, w/2 + 75, w/2 + 100,
                w/2 + 125, w/2 + 150, w/2 + 125, w/2 + 150, w/2 + 125, w/2 + 150, w/2 + 100};
        ys = new int[]{h/2 - 100, h/2 - 120, h/2 - 150, h/2 - 170, h/2 -200, h/2 - 220, h/2 - 250, h/2 - 270,
                h/2 - 250, h/2 - 220, h/2 - 200, h/2 - 170, h/2 - 150, h/2 - 120, h/2 - 75};
        lHorn = new Polygon(xs, ys, xs.length);

        xs = new int[]{w/2 - 100, w/2 - 75, w/2 - 25, w/2 - 50};
        ys = new int[]{h/2 - 70, h/2 - 100, h/2 - 50, h/2 - 20};
        lBrow = new Polygon(xs, ys , 4);

        xs = new int[]{w/2 - 25, w/2, w/2 + 50, w/2 + 25};
        ys = new int[]{h/2 - 50, h/2 - 25, h/2 - 80, h/2 - 100};
        rBrow = new Polygon(xs, ys, 4);
    }

    public void drawS(Diamond d, Graphics2D g2) {
        if (face.contains(d.x, d.y) ||
                rEye.contains(d.x, d.y) || lEye.contains(d.x, d.y) ||
                mouth.contains(d.x, d.y) ||
                rHorn.contains(d.x, d.y) || lHorn.contains(d.x, d.y)) {

            if (rEye.contains(d.x, d.y) || lEye.contains(d.x, d.y)) {
                d.draw(g2, new Color(255, 0, 0));
            } else if (lHorn.contains(d.x, d.y) || rHorn.contains(d.x, d.y) ||
                    mouth.contains(d.x, d.y) ||
                    rBrow.contains(d.x, d.y) || lBrow.contains(d.x, d.y)) {
                d.draw(g2, new Color(25, 25, 25));
            } else {
                d.draw(g2, new Color(255, 255, 255));
            }

        } else {
            d.draw(g2, new Color(255, 0,0));
        }
    }
}
