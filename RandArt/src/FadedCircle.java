import java.awt.*;

/**
 * Created by michael_hopps on 4/13/18.
 */
public class FadedCircle {

    private Color colorA, colorB;
    private int x, y;  //CENTER of this circle
    private int radius;

    private RadialGradientPaint paint;

    public FadedCircle(int x, int y, int radius, Color a, Color b) {
        this.colorA = a;
        this.colorB = b;
        this.x = x;
        this.y = y;
        this.radius = radius;

        float[] borders = {.33F, .66F};
        Color[] colors = {colorA, colorB};
        paint = new RadialGradientPaint(x, y, radius, borders, colors);

        /*
        RadialGradientPaint sets a "color" to a fade from one color to the next,
        starting from the center out to the edge.  A full transition from one color
        to the next occurs based on the borders array - these are percentages through
        the radius.
         */

    }

    public void draw(Graphics2D g2){

        g2.setPaint(paint);

        g2.fillOval(x-radius, y-radius, radius*2, radius*2);
    }



}