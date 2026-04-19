package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;

public class Vect2D extends Point2D {

    public Vect2D(double x, double y) { super(x, y); }
    public Vect2D() { super(); }

    /**
     * Berekent de lengte (magnitude) van de vector.
     */
    public double getLength() {
        // Correctie: x * x + y * y
        // Gebruik getters als x en y private zijn in Point2D,
        // of gebruik 'this.x' als ze protected zijn.
        return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
    }

    /**
     * Alternatieve methode voor betere precisie bij zeer grote of kleine getallen.
     * Voorkomt overflow/underflow tijdens het kwadrateren.
     */
    public double getLengthPrecise() {
        return Math.hypot(this.getX(), this.getY());
    }

    /**
     * Telt een andere vector op bij deze vector.
     * @param other De vector die opgeteld moet worden.
     */
    public void add(Vect2D other) {
        this.setX(this.getX() + other.getX());
        this.setY(this.getY() + other.getY());
    }

    /**
     * Maakt een nieuwe vector aan die de som is van deze en een andere vector.
     */
    public Vect2D plus(Vect2D other) {
        return new Vect2D(this.getX() + other.getX(), this.getY() + other.getY());
    }

    /**
     * Calculates the perpendicular (normal) vector of V and stores it in N.
     * This rotates the vector 90 degrees counter-clockwise.
     * * @param v The original vector.
     * @param n The vector object that will be updated with the normal coordinates.
     */
    public static Vect2D vectN(Vect2D v) {

        Vect2D n = new Vect2D();

        // Standard 2D perpendicular rotation: (-y, x)
        double tempX = -v.getY();
        double tempY = v.getX();

        n.setX(tempX);
        n.setY(tempY);

        return n;
    }

}



