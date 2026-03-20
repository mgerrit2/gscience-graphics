package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;
import com.gscience.graphics.gscience_graphics.geom2d.exception.ErrorCode;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;


public class Lines2D {

    /**
     * Finds the intersection point of two 2D lines using Cramer's Rule.
     * * <p>The lines are assumed to be in the general form: Ax + By + C = 0.
     * This method solves the system of linear equations by calculating the
     * determinant of the coefficient matrix.</p>
     *
     * @param l1 The first line for intersection.
     * @param l2 The second line for intersection.
     * @return A {@link Point2D} representing the unique point where the lines cross.
     * @throws LineLineException If the determinant is near zero, indicating the
     * lines are parallel or collinear and do not have a unique intersection.
     */
    public static Point2D pointLL2d(Line2D l1, Line2D l2) throws LineLineException {
        double det = l1.getA() * l2.getB() - l2.getA() * l1.getB();
        if (Math.abs(det) < GeomConstants.EPS6) {
            throw new LineLineException("Parallel lines",ErrorCode.PARALLEL_LINES);
        } else {
            double det1 = l1.getA() * l1.getY() - l1.getB() * l1.getX();
            double det2 = l2.getA() * l2.getY() - l2.getB() * l2.getX();
            double detX = -l1.getA() * det2 + l2.getA() * det1;
            double detY = l2.getB() * det1 - l1.getB() * det2;
            return new Point2D(detX / det, detY / det);
        }
    }



}
