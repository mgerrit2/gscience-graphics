package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.exception.ErrorCode;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointEE2DTest {

        @Test
        @DisplayName("Should find intersection of two segments crossing at (5,5)")
        void testStandardIntersection() throws LineLineException {
            // Segment 1: (0,0) to (10,10)
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(10, 10);

            // Segment 2: (0,10) to (10,0)
            Point2D p3 = new Point2D(0, 10);
            Point2D p4 = new Point2D(10, 0);

            Point2D result = Points2D.pointEE2d(p1, p2, p3, p4);

            assertEquals(5.0, result.getX(), 1e-9);
            assertEquals(5.0, result.getY(), 1e-9);
        }

        @Test
        @DisplayName("Should find intersection when segments share an endpoint")
        void testSharedEndpoint() throws LineLineException {
            // Two segments meeting at (2,2)
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(2, 2); // Intersection point

            Point2D p3 = new Point2D(2, 2); // Intersection point
            Point2D p4 = new Point2D(4, 0);

            Point2D result = Points2D.pointEE2d(p1, p2, p3, p4);

            assertEquals(2.0, result.getX(), 1e-9);
            assertEquals(2.0, result.getY(), 1e-9);
        }

        @Test
        @DisplayName("Should find intersection of infinite lines even if segments don't physically touch")
        void testNonTouchingSegments() throws LineLineException {
            // Segment 1: x-axis from 0 to 2
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(2, 0);

            // Segment 2: vertical line at x=5 from y=1 to y=2
            Point2D p3 = new Point2D(5, 1);
            Point2D p4 = new Point2D(5, 2);

            // These don't "touch", but the infinite lines intersect at (5,0)
            Point2D result = Points2D.pointEE2d(p1, p2, p3, p4);

            assertEquals(5.0, result.getX(), 1e-9);
            assertEquals(0.0, result.getY(), 1e-9);
        }

        @Test
        @DisplayName("Should throw Parallel Lines exception for parallel segments")
        void testParallelSegments() {
            // Two horizontal segments
            Point2D p1 = new Point2D(0, 0);
            Point2D p2 = new Point2D(5, 0);

            Point2D p3 = new Point2D(0, 2);
            Point2D p4 = new Point2D(5, 2);

            LineLineException ex = assertThrows(LineLineException.class, () -> {
                Points2D.pointEE2d(p1, p2, p3, p4);
            });

            assertEquals(ErrorCode.PARALLEL_LINES, ex.getErrorCode());
        }
}
