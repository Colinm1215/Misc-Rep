import java.awt.*;

class Diamond {
    private final int size;
    private final Color color;
    public final int x;
    public final int y;

    public Diamond(int x, int y, int size) {
        int red = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        color = new Color(red, blue, green);
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g, Color c) {
        if (c == null) {
            g.setColor(color);
            int[] xs = {x, x, x - (size / 2)};
            int[] ys = {y, y - (size / 2), y};
            g.fillPolygon(xs, ys, 3);
            g.setColor(color.darker());
            xs = new int[]{x, x, x - (size / 2)};
            ys = new int[]{y, y + (size / 2), y};
            g.fillPolygon(xs, ys, 3);
            g.setColor(color.brighter());
            xs = new int[]{x, x, x + (size / 2)};
            ys = new int[]{y, y - (size / 2), y};
            g.fillPolygon(xs, ys, 3);
            g.setColor(color.darker().darker());
            xs = new int[]{x, x, x + (size / 2)};
            ys = new int[]{y, y + (size / 2), y};
            g.fillPolygon(xs, ys, 3);
        } else {
            g.setColor(c);
            int[] xs = {x, x, x - (size / 2)};
            int[] ys = {y, y - (size / 2), y};
            g.fillPolygon(xs, ys, 3);
            g.setColor(c.darker());
            xs = new int[]{x, x, x - (size / 2)};
            ys = new int[]{y, y + (size / 2), y};
            g.fillPolygon(xs, ys, 3);
            g.setColor(c.brighter());
            xs = new int[]{x, x, x + (size / 2)};
            ys = new int[]{y, y - (size / 2), y};
            g.fillPolygon(xs, ys, 3);
            g.setColor(c.darker().darker());
            xs = new int[]{x, x, x + (size / 2)};
            ys = new int[]{y, y + (size / 2), y};
            g.fillPolygon(xs, ys, 3);
        }
    }

}
