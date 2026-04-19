package com.gscience.graphics.gscience_graphics.geom2d;

import lombok.Data;

@Data
public class Line2D {

    // In Pascal: True: (a, b, x, y) or False: (v, p)
    private Point2D p;    // The anchor point (x, y)
    private Point2D v;    // The direction vector (a, b)

    public Line2D() {
        this.p = new Point2D();
        this.v = new Point2D();
    }

    public Line2D(Point2D p, Point2D v) {
        this.p = new Point2D(p);
        this.v = new Point2D(v);
    }

    public Line2D(double a, double b, double x, double y) {
        this.p = new Point2D(x, y); // The anchor
        this.v = new Point2D(a, b); // The direction vector
    }

    /**
     * Pascal: FUNCTION Length_L_2d (L:Line_2d):Real;
     * Calculates the length of the direction vector.
     */
    public double length() {
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
    }

    @Override
    public String toString() {
        return String.format(".v[a,b]= %9.6f,%9.6f\n.p[x,y]= %9.6f,%9.6f",
                v.getX(), v.getY(), p.getX(), p.getY());
    }

    // Pascal variant record represented by providing all fields
    /*
    private double a, b; // Vector components
    private double x, y; // Base point coordinates

    public Line2D(double a, double b, double x, double y) {
        this.a = a; this.b = b; this.x = x; this.y = y;
    }
*/


}
