package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;

public class Points2D {


    /**
     * Calculates the midpoint between two points.
     * Pascal: PROCEDURE Point_PP_2d (P1,P2:Point_2d; VAR P:Point_2d);
     * * @param p1 The first point
     * @param p2 The second point
     * @return A new Point2D representing the midpoint
     */
    public static Point2D pointPP2d(Point2D p1, Point2D p2) {
        double midX = (p1.getX() + p2.getX()) / 2.0;
        double midY = (p1.getY() + p2.getY()) / 2.0;

        return new Point2D(midX, midY);
    }


    /**
     * Calculates a point P that divides the line segment P1P2 in a specified ratio a1:a2.
     * * <p>This method implements the Section Formula for 2D Euclidean space.
     * If a1 and a2 are both positive, the point P lies internally between P1 and P2.
     * If one value is negative, the point P lies externally on the line defined by P1 and P2.</p>
     * * <p>The mathematical formula used is:
     * P = (P1 * a2 + P2 * a1) / (a1 + a2)</p>
     *
     * @param p1     The starting point of the segment.
     * @param p2     The ending point of the segment.
     * @param a1     The ratio part corresponding to the distance from P1 to P.
     * @param a2     The ratio part corresponding to the distance from P to P2.
     * @return       A new {@link Point2D} representing the division point.
     * @throws IllegalArgumentException if the sum of a1 and a2 is zero (or within epsilon),
     * as this would result in a division by zero (point at infinity).
     */
    public static Point2D sect2d(Point2D p1, Point2D p2, double a1, double a2) {
        double divisor = a1 + a2;

        // Check for division by zero (parallel to Pascal's Eps6 check)
        if (Math.abs(divisor) < GeomConstants.EPS6) {
            throw new IllegalArgumentException("Sect_2d: a1 + a2 = 0");
        }

        // The formula: P = (P1*a2 + P2*a1) / (a1 + a2)
        double px = (p1.getX() * a2 + p2.getX() * a1) / divisor;
        double py = (p1.getY() * a2 + p2.getY() * a1) / divisor;

        return new Point2D(px, py);
    }

    /**
     * Rotation around a center point C
     * @param p0
     * @param c
     * @param sinh
     * @param cosh
     * @return
     */
    public Point2D rotC2d(Point2D p0, Point2D c, double sinh, double cosh) {
        double x = p0.getX() * cosh - p0.getY() * sinh - c.getX() * cosh + c.getY() * sinh + c.getX();
        double y = p0.getX() * sinh + p0.getY() * cosh - c.getX() * sinh - c.getY() * cosh + c.getY();
        return new Point2D(x, y);
    }

    /**
     * Scaling relative to a center point C
     * @param p0
     * @param c
     * @param sx
     * @param sy
     * @return
     */
    public Point2D scalC2d(Point2D p0, Point2D c, double sx, double sy) {
        double x = (p0.getX() - c.getX()) * sx + c.getX();
        double y = (p0.getY() - c.getY()) * sy + c.getY();
        return new Point2D(x, y);
    }


    //region  Point in Polygon
    /**
     * Point in Polygon
     *
     * The InPoly_2d function determines if a point is inside a polygon based on side-consistency.
     *
     * @param poly
     * @param p
     * @return
     */
    public boolean inPoly2d(Point2D[] poly, Point2D p) {
        boolean inp = true;
        int n = poly.length;
        for (int i = 0; i < n; i++) {
            int j = (i < n - 1) ? i + 1 : 0;
            int k = (i < n - 2) ? i + 2 : (i == n - 2 ? 0 : 1);

            boolean upperK = upperPP2d(poly[k], poly[i], poly[j]);
            boolean upperP = upperPP2d(p, poly[i], poly[j]);

            if (upperK != upperP) {
                inp = false;
                break;
            }
        }
        return inp;
    }

    private boolean upperPP2d(Point2D p, Point2D p1, Point2D p2) {
        double w = (p.getX() - p1.getX()) * (p2.getY() - p1.getY()) - (p2.getX() - p1.getX()) * (p.getY() - p1.getY());
        return w >= 0;
    }
    //endregion

}
