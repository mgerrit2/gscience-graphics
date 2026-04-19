package com.gscience.graphics.gscience_graphics.geom2d;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Sect2dTest {


        @Test
        @DisplayName("Should find the exact midpoint when ratio is 1:1")
        void testMidpoint() {
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(10, 10);

            Point2D result = Points2D.sect(p1, p2, 1.0, 1.0);

            assertEquals(5.0, result.getX(), 1e-9);
            assertEquals(5.0, result.getY(), 1e-9);
        }

        @Test
        @DisplayName("Should find a point 25% along the segment when ratio is 1:3")
        void testWeightedSection() {
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(100, 40);

            // a1=1, a2=3 means P is 1/4 of the way from P1 to P2
            Point2D result = Points2D.sect(p1, p2, 1.0, 3.0);

            assertEquals(25.0, result.getX(), 1e-9);
            assertEquals(10.0, result.getY(), 1e-9);
        }


        @Test
        @DisplayName("Should return P2 when a2 is 0")
        void testRatioToEndpoint() {
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(5, 5);

            Point2D result = Points2D.sect(p1, p2, 1.0, 0.0);

            assertEquals(5.0, result.getX(), 1e-9);
            assertEquals(5.0, result.getY(), 1e-9);
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when a1 + a2 = 0")
        void testDivisionByZero() {
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(10, 10);

            assertThrows(IllegalArgumentException.class, () -> {
                Points2D.sect(p1, p2, 5.0, -5.0);
            });
        }

}
