package com.gscience.graphics.gscience_graphics.geom2d;

import lombok.Data;

@Data
public class Line2D {

    // Pascal variant record represented by providing all fields
    private double a, b; // Vector components
    private double x, y; // Base point coordinates

    public Line2D(double a, double b, double x, double y) {
        this.a = a; this.b = b; this.x = x; this.y = y;
    }

}
