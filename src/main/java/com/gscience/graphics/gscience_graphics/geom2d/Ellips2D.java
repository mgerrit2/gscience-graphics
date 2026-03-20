package com.gscience.graphics.gscience_graphics.geom2d;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ellips2D {

    private double a; // Semi-major axis
    private double b; // Semi-minor axis
    private Point2D p = new Point2D(); // Center point

    // Constructor mimicking the 'False' case (passing a Point object)
    public Ellips2D(double a, double b, Point2D p) {
        this.a = a;
        this.b = b;
        this.p = (p != null) ? p : new Point2D();
    }

    // Constructor mimicking the 'True' case (passing x, y coordinates)
    public Ellips2D(double a, double b, double x, double y) {
        this.a = a;
        this.b = b;
        this.p = new Point2D(x, y);
    }

}
