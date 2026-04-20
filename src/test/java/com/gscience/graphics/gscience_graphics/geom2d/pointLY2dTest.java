package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.GeomConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pointLY2dTest {

    @Test
    @DisplayName("Should correctly calculate X on a diagonal line")
    void testStandardCalculation() {
        // Line through (10, 10) with inverse slope (a/b) of 2.0
        Line2D line = new Line2D(2.0, 1.0, 10.0, 10.0);
        Point2D point = new Point2D(0.0, 15); // We provide Y = 15

        Point2D result = Points2D.pointLY2d(line, point);

        // Calculation: (2.0 / 1.0) * (15.0 - 10.0) + 10.0 = 20.0
        assertEquals(20.0, point.getX(), GeomConstants.EPS6);
        assertSame(point, result, "The method should return the same object instance");
    }

    @Test
    @DisplayName("Should handle horizontal lines where a is 0")
    void testHorizontalLine() {
        // If a=0, x always equals L.x regardless of Y
        Line2D line = new Line2D(0.0, 1.0, 5.0, 5.0);
        Point2D point = new Point2D(0.0, 100.0);

        Points2D.pointLY2d(line, point);

        assertEquals(5.0, point.getX(), GeomConstants.EPS6);
    }

    @Test
    @DisplayName("Should throw ArithmeticException for vertical lines (b near zero)")
    void testVerticalLineException() {
        Line2D verticalLine = new Line2D(1.0, 0.0, 10.0, 10.0);
        Point2D point = new Point2D(0.0, 5.0);

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Points2D.pointLY2d(verticalLine, point);
        });

        assertTrue(exception.getMessage().contains("Division by zero"));
    }

}
