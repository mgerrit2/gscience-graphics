package com.gscience.graphics.gscience_graphics.geom2d;



public record PolarPoint(double r, double ph) {
    public double getR() { return r; }
    public double getPh() { return ph; }
}
