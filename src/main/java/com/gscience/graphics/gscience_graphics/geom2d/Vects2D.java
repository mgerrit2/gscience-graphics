package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;

public class Vects2D{

    /**
     * Trekt vector V2 af van V1 (V = V1 - V2) en slaat het resultaat op in V.
     * @param v1 De linker vector (minuend).
     * @param v2 De rechter vector (aftrekker).
     * @param v  De doelvector die het verschil bevat.
     */
    public static Vect2D vectSub2d(Vect2D v1, Vect2D v2) {

        Vect2D v = new Vect2D();

        v.setX(v1.getX() - v2.getX());
        v.setY(v1.getY() - v2.getY());

        return v;
    }

    /**
     * Calculates the perpendicular (normal) vector of V and stores it in N.
     * This rotates the vector 90 degrees counter-clockwise.
     * * @param v The original vector.
     * @param n The vector object that will be updated with the normal coordinates.
     */
    public static Vect2D vectN2d(Vect2D v) {

        Vect2D n = new Vect2D();

        // Standard 2D perpendicular rotation: (-y, x)
        double tempX = -v.getY();
        double tempY = v.getX();

        n.setX(tempX);
        n.setY(tempY);

        return n;
    }

    /**
     * Berekent de eenheidsvector (Unit Vector) van V1 en slaat het resultaat op in V.
     * Als de lengte van de vector bijna nul is, wordt de resulterende vector op (0,0) gezet.
     * * @param v1 De bronvector die genormaliseerd moet worden.
     * @param v  De doelvector waarin het resultaat wordt opgeslagen.
     */
    public static void vectU2d(Vect2D v1, Vect2D v) {
        double len = v1.getLength();

        // Controleer op een lengte nabij nul om "Division by Zero" te voorkomen
        if (Math.abs(len) < GeomConstants.EPS9) {
            v.setX(0.0);
            v.setY(0.0);
        } else {
            v.setX(v1.getX() / len);
            v.setY(v1.getY() / len);
        }
    }

    /**
     * Berekent het inwendig product (dot product) van twee vectoren.
     * @param v1 De eerste vector.
     * @param v2 De tweede vector.
     * @return Het scalaire resultaat (v1.x * v2.x + v1.y * v2.y).
     */
    public static double vectProdS2d(Vect2D v1, Vect2D v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    /**
     * Vermenigvuldigt vector V1 met een scalair r (V = V1 * r) en slaat het resultaat op in V.
     * @param v1 De bronvector.
     * @param r  De schaalfactor (double).
     * @param v  De doelvector die het geschaalde resultaat bevat.
     */
    public static Vect2D vectMultS2d(Vect2D v1, double r) {

        Vect2D v = new Vect2D();

        v.setX(v1.getX() * r);
        v.setY(v1.getY() * r);

        return v;
    }

}
