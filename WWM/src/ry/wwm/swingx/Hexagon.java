package ry.wwm.swingx;

import java.awt.Polygon;

/**
 *
 * @author ry
 */
public final class Hexagon extends Polygon {

    public Hexagon(int width, int height) {
        final int w = width - 1;
        final int h = height - 1;
        final int ratio = (int) (h * .25);

        addPoint(0, h / 2);
        addPoint(ratio, h);
        addPoint(w - ratio, h);
        addPoint(w, h / 2);
        addPoint(w - ratio, 0);
        addPoint(ratio, 0);
    }
}
