package com.gscience.graphics.gscience_graphics.geom2d;

import lombok.Data;

@Data
public class Circle2D {
    private double r;
    private Point2D p;
    public Circle2D(double r, Point2D p) { this.r = r; this.p = p; }
}
