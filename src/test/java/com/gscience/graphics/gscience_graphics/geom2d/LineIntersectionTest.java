package com.gscience.graphics.gscience_graphics.geom2d;

import static org.junit.jupiter.api.Assertions.*;

import com.gscience.graphics.gscience_graphics.geom2d.exception.ErrorCode;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class LineIntersectionTest {

    @Test
    @DisplayName("Should find intersection of perpendicular lines at (0,0)")
    void testPerpendicularIntersection() {
        // Line 1: y = 0 -> 0x + 1y + 0 = 0 (A=0, B=1, P=(0,0))
        Line2D l1 = new Line2D(0, 0, 0, 1);
        // Line 2: x = 0 -> 1x + 0y + 0 = 0 (A=1, B=0, P=(0,0))
        Line2D l2 = new Line2D(0, 0, 1, 0);

        assertDoesNotThrow(() -> {
            Point2D result = Lines2D.pointLL2d(l1, l2);
            assertEquals(0.0, result.getX(), 1e-9);
            assertEquals(0.0, result.getY(), 1e-9);
        });
    }

    @Test
    @DisplayName("Should find intersection for oblique lines")
    void testObliqueIntersection() {
        // Example: y = x and y = -x + 2 -> Intersect at (1,1)
        Line2D l1 = new Line2D(0, 0, 1, -1); // x - y = 0
        Line2D l2 = new Line2D(0, 2, 1, 1);  // x + y - 2 = 0

        try {
            Point2D result = Lines2D.pointLL2d(l1, l2);
            assertEquals(1.0, result.getX(), 1e-9);
            assertEquals(1.0, result.getY(), 1e-9);
        } catch (LineLineException e) {
            fail("Lines should intersect");
        }
    }

    @Test
    @DisplayName("Should throw exception for parallel lines")
    void testParallelLines() {
        // Line 1: y = 5
        Line2D l1 = new Line2D(0, 5, 0, 1);
        // Line 2: y = 10
        Line2D l2 = new Line2D(0, 10, 0, 1);

        LineLineException exception = assertThrows(LineLineException.class, () -> {
            Lines2D.pointLL2d(l1, l2);
        });

        assertEquals(ErrorCode.PARALLEL_LINES, exception.getErrorCode());
    }

    @Test
    @DisplayName("Should throw exception for identical (collinear) lines")
    void testCollinearLines() {
        Line2D l1 = new Line2D(0, 0, 1, 1);
        Line2D l2 = new Line2D(0, 0, 1, 1);

        assertThrows(LineLineException.class, () -> Lines2D.pointLL2d(l1, l2));
    }
}
