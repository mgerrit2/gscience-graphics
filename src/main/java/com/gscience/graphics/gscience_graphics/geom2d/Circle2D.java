package com.gscience.graphics.gscience_graphics.geom2d;

import lombok.Data;

@Data
public class Circle2D {
    private double r;
    private Point2D p;
    public Circle2D(double r, Point2D p) { this.r = r; this.p = p; }


    /**
     * Determent the cutting point of a line over a circle
     * point_OL
     * @param c
     * @param l
     */
    public void pointOL(Circle2D c,Line2D l){
        Point2D p1 = new Point2D();
        Point2D p2 = new Point2D();

        Vect2D V = new Vect2D();
        Vect2D N = new Vect2D();

        Line2D Ln = new Line2D();


    }

}
