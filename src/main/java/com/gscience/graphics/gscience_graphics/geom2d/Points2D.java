package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;

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
     * Finds the intersection point of two lines defined by two pairs of points (P1-P2 and P3-P4).
     * * <p>This method converts the point pairs into {@link Line2D} objects using the vector
     * direction (P2 - P1) and (P4 - P3), then solves for the intersection using Cramer's Rule via
     * {@link #pointLL2d(Line2D, Line2D)}.</p>
     * * <p>Note: While the Pascal original (Point_EE_2d) often includes range checking to see if
     * the intersection lies strictly within the segments, this implementation currently returns
     * the intersection of the infinite lines passing through those points.</p>
     *
     * @param p1 The start point of the first segment.
     * @param p2 The end point of the first segment.
     * @param p3 The start point of the second segment.
     * @param p4 The end point of the second segment.
     * @return   A {@link Point2D} representing the intersection of the two lines.
     * @throws LineLineException If the segments are parallel (det < EPS6), as no unique
     * intersection exists.
     * @see Points2D#linePP2d(Point2D, Point2D)
     * @see #pointLL2d(Line2D, Line2D)
     */
    public static Point2D pointEE2d(Point2D p1, Point2D p2, Point2D p3, Point2D p4) throws LineLineException {
        Line2D l1 = Points2D.linePP2d(p1, p2);
        Line2D l2 = Points2D.linePP2d(p3, p4);

        try {
            // Find intersection of the infinite lines
            Point2D p = Lines2D.pointLL2d(l1, l2);
            return p;
        } catch (LineLineException e) {
            // If pointLL2d failed (Parallel lines), Pascal logic suggests
            // further checks, though usually parallel segments don't intersect.
            throw e;
        }
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

    /**
     * Creates a {@link Line2D} defined by two points, P1 and P2.
     * * <p>This method calculates the direction vector (a, b) as the difference
     * between the coordinates of P2 and P1, and sets P1 as the base anchor point (x, y).</p>
     * * <p>The resulting line can be represented parametrically as:
     * <br>L(t) = (p1.x + t * a, p1.y + t * b)</p>
     *
     * @param p1 The starting or "base" point of the line.
     * @param p2 A second point used to determine the direction of the line.
     * @return   A new {@link Line2D} instance with vector components (a, b)
     * and base coordinates (x, y).
     * @throws NullPointerException if p1 or p2 is null.
     */
    public static Line2D linePP2d(Point2D p1, Point2D p2) {
        // Direction vector (a, b) = P2 - P1
        double a = p2.getX() - p1.getX();
        double b = p2.getY() - p1.getY();
        // Base point is p1
        return new Line2D(a, b, p1.getX(), p1.getY());
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

    /**
     * Calculates the Y coordinate on a line given an X coordinate.
     * Uses the point-slope form: y = m(x - x1) + y1, where m = b/a.
     *
     * @param l
     * @param p
     * @return
     * @See Grafische toepassingen in turbo pascal blz 22
     */
    public static Point2D pointLX2d(Line2D l, Point2D p) {
        // Guard Clause: Prevent division by zero for vertical lines
        if (Math.abs(l.getA()) < GeomConstants.EPS6) {
            throw new ArithmeticException("Division by zero: Line 'a' component is too small (Vertical Line).");
        }

        // Calculate slope m = rise/run
        double slope = l.getB() / l.getA();

        // Calculate new Y
        double newY = slope * (p.getX() - l.getX()) + l.getY();

        // Update the existing point
        p.setY(newY);

        // Return the updated point (allows for method chaining)
        return p;
    }


    /**
     * Calculates the X-coordinate of a point on the line given its Y-coordinate.
     * * @param l The 2D line definition containing components a, b and anchor point (x, y).
     * @param p The point containing the target Y-coordinate; its X-coordinate will be updated.
     * @return The updated Point2D object for method chaining.
     * @throws ArithmeticException if the line's 'b' component is near zero (vertical line).
     * @See Grafische toepassingen in turbo pascal blz 23
     */
    public static Point2D pointLY2d(Line2D l, Point2D p) {
        if (Math.abs(l.getB()) < GeomConstants.EPS6) {
            throw new ArithmeticException("Division by zero: Line 'a' component is too small (Vertical Line).");
        } else {
            // Calculation: P.x = (L.a / L.b) * (P.y - L.y) + L.x
            p.setX((l.getA() / l.getB()) * (p.getY() - l.getY()) + l.getX());
            return p;
        }
    }

}
