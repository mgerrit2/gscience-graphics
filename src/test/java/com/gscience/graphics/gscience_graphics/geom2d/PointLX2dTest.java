package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointLX2dTest {

    @Test
    @DisplayName("Should calculate correct Y for a 45-degree diagonal line")
    void testStandardDiagonalLine() {
        // Line through (2, 3) with a=1, b=1 (Slope m = 1)
        Line2D line = new Line2D(1.0, 1.0, 2.0, 3.0);
        // We want to find Y at X = 10
        Point2D point = new Point2D(1.5, 0.0);

        Points2D.pointLX2d(line, point);

        // Expected Y: 1 * (10 - 2) + 3 = 11
        assertEquals(2.5, point.getY(), GeomConstants.EPS6);
    }

    @Test
    @DisplayName("Should calculate correct Y for a horizontal line (b=0)")
    void testHorizontalLine() {
        // Line through (0, 5) with a=1, b=0 (Slope m = 0)
        Line2D line = new Line2D(1.0, 0.0, 0.0, 5.0);
        Point2D point = new Point2D(50.0, 0.0);

        Points2D.pointLX2d(line, point);

        // Expected Y: always 5.0
        assertEquals(5.0, point.getY(), GeomConstants.EPS6);
    }

    @Test
    @DisplayName("Should throw ArithmeticException for vertical lines (a=0)")
    void testVerticalLineException() {
        // Line with a=0 (Vertical)
        Line2D verticalLine = new Line2D(0.0, 1.0, 5.0, 5.0);
        Point2D point = new Point2D(10.0, 0.0);

        assertThrows(ArithmeticException.class, () -> {
            Points2D.pointLX2d(verticalLine, point);
        });
    }

    @Test
    @DisplayName("Should return the same point object for method chaining")
    void testMethodChaining() {
        Line2D line = new Line2D(1.0, 1.0, 0.0, 0.0);
        Point2D point = new Point2D(5.0, 0.0);

        Point2D result = Points2D.pointLX2d(line, point);

        // Verify it's the exact same instance in memory
        assertSame(point, result);
    }

}
