package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.IntersectionStatus;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;
import com.gscience.graphics.gscience_graphics.geom2d.result.IntersectionResult;
import lombok.Data;

@Data
public class Circle2D {

    private double r;

    private Point2D p;

    public Circle2D(double r, Point2D p) {
        if (r < 0) {
            throw new IllegalArgumentException("Radius cannot be negative: " + r);
        }
        this.r = r; this.p = p;
    }


    /**
     * Determent the cutting point of a line over a circle
     * point_OL
     * @param c
     * @param l
     */
    public IntersectionResult pointOL2D(Circle2D c, Line2D l) throws LineLineException {
        final double EPS6 = 1e-6;

        // 1. Get the normal vector of the line: N = (-l.v.y, l.v.x)
        Point2D n = new Point2D(-l.getV().getY(), l.getV().getX());

        // 2. Create a perpendicular line (Ln) passing through the circle center (C.p)
        Line2D ln = new Line2D(c.getP(), n);

        // 3. Find the projection point P0 (closest point on line L to the circle center)
        Point2D p0 = Lines2D.pointLL(l, ln);

        // 4. Distance check
        double d = Points2D.distPP(p,p0);

        if (this.r < d - EPS6) {
            return new IntersectionResult(IntersectionStatus.NO_INTERSECTION, null, null);
        }

        // Determine status
        IntersectionStatus status = (Math.abs(this.r - d) < EPS6)
                ? IntersectionStatus.TANGENT
                : IntersectionStatus.INTERSECT;

        double rChord = Math.sqrt(Math.max(0, (this.r * this.r) - (d * d)));

        // Vector logic
        double len = l.length();
        Point2D vUnit = new Point2D(l.getV().getX() / len, l.getV().getY() / len);
        Point2D offset = new Point2D(vUnit.getX() * rChord, vUnit.getY() * rChord);

        return new IntersectionResult(
                status,
                new Point2D(p0.getX() + offset.getX(), p0.getY() + offset.getY()),
                new Point2D(p0.getX() - offset.getX(), p0.getY() - offset.getY())
        );
    }

    public double getRadius() { return r; }
    public Point2D getCenter() { return p; }

}
