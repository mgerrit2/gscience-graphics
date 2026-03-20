package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;
import com.gscience.graphics.gscience_graphics.geom2d.exception.ErrorCode;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;


public class Lines2D {

    /**
     * Point and Line Intersections
     *
     * The Point_LL_2d procedure uses Cramer's rule to find where two lines intersect.
     * @param l1
     * @param l2
     * @return
     */
    public Point2D pointLL2d(Line2D l1, Line2D l2) throws LineLineException {
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
