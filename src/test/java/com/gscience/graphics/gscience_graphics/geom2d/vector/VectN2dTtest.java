package com.gscience.graphics.gscience_graphics.geom2d.vector;

import com.gscience.graphics.gscience_graphics.geom2d.Vect2D;
import com.gscience.graphics.gscience_graphics.geom2d.Vects2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectN2dTtest {

    @Test
    @DisplayName("Should rotate a standard vector 90 degrees CCW")
    void testStandardRotation() {
        Vect2D v = new Vect2D(5.0, 2.0);
        Vect2D n = new Vect2D(0.0, 0.0);

        var result = Vects2D.vectN2d(v);

        // (5, 2) should become (-2, 5)
        assertEquals(-2.0, result.getX(), 1e-9);
        assertEquals(5.0, result.getY(), 1e-9);
    }

    @Test
    @DisplayName("Should handle vectors in different quadrants")
    void testQuadrants() {
        Vect2D v = new Vect2D(-3.0, -4.0);
        Vect2D n = new Vect2D();

        var result =Vects2D.vectN2d(v);

        // (-3, -4) should become (4, -3)
        assertEquals(4.0, result.getX(), 1e-9);
        assertEquals(-3.0, result.getY(), 1e-9);
    }

    @Test
    @DisplayName("Should work correctly when v and n are the same object")
    void testSelfMutation() {
        Vect2D v = new Vect2D(1.0, 0.0);

        // Passing the same reference for both parameters
        var result = Vects2D.vectN2d(v);

        // (1, 0) should become (0, 1)
        assertEquals(0.0, result.getX(), 1e-9);
        assertEquals(1.0, result.getY(), 1e-9);
    }

    @Test
    @DisplayName("Should result in a dot product of zero (orthogonality check)")
    void testOrthogonality() {
        Vect2D v = new Vect2D(Math.random(), Math.random());
        Vect2D n = new Vect2D();

        var result = Vects2D.vectN2d(v);

        // The dot product of perpendicular vectors is always 0: (x1*x2 + y1*y2)
        double dotProduct = (v.getX() * n.getX()) + (v.getY() * n.getY());

        assertEquals(0.0, dotProduct, 1e-9, "Normal vector must be orthogonal to original");
    }

}
