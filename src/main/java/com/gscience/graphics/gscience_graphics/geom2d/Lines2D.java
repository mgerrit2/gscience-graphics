package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;
import com.gscience.graphics.gscience_graphics.geom2d.exception.ErrorCode;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;


public class Lines2D {

    /**
     * Finds the intersection point of two 2D lines using Cramer's Rule.
     * <p>The lines are assumed to be in parametric form: P = P0 + tV,
     * where P0 is an anchor point and V is a direction vector.</p>
     *
     * @param l1 The first line (Point P1, Vector V1).
     * @param l2 The second line (Point P2, Vector V2).
     * @return A {@link Point2D} representing the intersection.
     * @throws LineLineException If the lines are parallel (determinant of vectors is 0).
     */
    public static Point2D pointLL(Line2D l1, Line2D l2) throws LineLineException {
        // Pascal: Det := L1.a * L2.b - L2.a * L1.b;
        double det = l1.getV().getX() * l2.getV().getY() - l2.getV().getX() * l1.getV().getY();

        if (Math.abs(det) < GeomConstants.EPS6) {
            throw new LineLineException("Parallel lines", ErrorCode.PARALLEL_LINES);
        } else {
            // Pascal: Det1 := L1.a * L1.y - L1.b * L1.x;
            double det1 = l1.getV().getX() * l1.getP().getY() - l1.getV().getY() * l1.getP().getX();

            // Pascal: Det2 := L2.a * L2.y - L2.b * L2.x;
            double det2 = l2.getV().getX() * l2.getP().getY() - l2.getV().getY() * l2.getP().getX();

            // Pascal: DetX := -L1.a * Det2 + L2.a * Det1;
            double detX = -l1.getV().getX() * det2 + l2.getV().getX() * det1;

            // Pascal: DetY := L2.b * Det1 - l1.b * Det2;
            double detY = l2.getV().getY() * det1 - l1.getV().getY() * det2;

            return new Point2D(detX / det, detY / det);
        }
    }


}
