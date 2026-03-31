package com.gscience.graphics.gscience_graphics.geom2d;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Point2D {

    private double x, y;
    public Point2D(double x, double y) { this.x = x; this.y = y; }
    public Point2D(Point2D p) {
        this.x = p.getX();
        this.y= p.getY();
    }
    public Point2D() { this(0, 0); }

    /**
     * Polygon Area
     *
     * The AreaPoly_2d procedure implements the "Shoelace Formula" to calculate the area of a
     * @param poly
     * @return
     */
    public double areaPoly2d(Point2D[] poly) {
        double area = 0;
        int n = poly.length;
        for (int i = 0; i < n; i++) {
            Point2D pI = poly[i];
            Point2D pJ = (i < n - 1) ? poly[i + 1] : poly[0];
            area += (pI.x - pJ.x) * (pI.y + pJ.y);
        }
        return area / 2.0;
    }

    /**
     * Converts Cartesian coordinates to Polar coordinates.
     * Pascal: PROCEDURE P_Polar_2d (P:Point_2d; VAR r,Ph:Real);
     */
    public PolarPoint toPolar2d(Point2D p) {
        // r = sqrt(x^2 + y^2)
        double r = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

        // Math.atan2(y, x) handles the quadrant logic automatically,
        // replacing the custom Pascal "Angle" function.
        double ph = Math.atan2(p.getY(), p.getX());

        return new PolarPoint(r, ph);
    }

}
