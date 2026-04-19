package com.gscience.graphics.gscience_graphics.geom2d;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class inRecG2dTest {

    @Test
    @DisplayName("Should return true for points inside the rectangle")
    void testPointInside() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(10, 10);
        Point2D p = new Point2D(5, 5);

        assertTrue(Points2D.inRecG2d(p1, p2, p), "Point (5,5) should be inside (0,0)-(10,10)");
    }

    @Test
    @DisplayName("Should work regardless of P1 and P2 order (Diagonal swap)")
    void testRectangleOrientation() {
        // P1 is top-right, P2 is bottom-left
        Point2D p1 = new Point2D(10, 10);
        Point2D p2 = new Point2D(0, 0);
        Point2D p = new Point2D(5, 5);

        assertTrue(Points2D.inRecG2d(p1, p2, p), "Should work even if P1 and P2 are swapped");
    }

    @Test
    @DisplayName("Should return true for points exactly on the boundary")
    void testPointOnBoundary() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(10, 10);

        Point2D edgePoint = new Point2D(0, 5);
        Point2D cornerPoint = new Point2D(10, 10);

        assertTrue(Points2D.inRecG2d(p1, p2, edgePoint), "Point on the edge should be inclusive");
        assertTrue(Points2D.inRecG2d(p1, p2, cornerPoint), "Point on the corner should be inclusive");
    }

    @Test
    @DisplayName("Should return false for points outside the rectangle")
    void testPointOutside() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(10, 10);

        assertAll("Outside points",
                () -> assertFalse(Points2D.inRecG2d(p1, p2, new Point2D(-1, 5)), "Point to the left should be false"),
                () -> assertFalse(Points2D.inRecG2d(p1, p2, new Point2D(11, 5)), "Point to the right should be false"),
                () -> assertFalse(Points2D.inRecG2d(p1, p2, new Point2D(5, -1)), "Point below should be false"),
                () -> assertFalse(Points2D.inRecG2d(p1, p2, new Point2D(5, 11)), "Point above should be false")
        );
    }
}
