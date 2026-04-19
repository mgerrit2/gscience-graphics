package com.gscience.graphics.gscience_graphics.geom2d;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Point2DTest {

    private static final double THRESHOLD = 1e-10;
    private static final double DELTA = 1e-10; // Precisie voor floating point vergelijkingen

    @Test
    void testToPolar2d() {
        // 1. Test Origin (0,0)
        assertPolar(0, 0, 0, 0);

        // 2. Test Positive X-axis (1,0) -> 0 degrees
        assertPolar(1, 0, 1.0, 0.0);

        // 3. Test Positive Y-axis (0,1) -> 90 degrees (PI/2)
        assertPolar(0, 1, 1.0, Math.PI / 2);

        // 4. Test Negative X-axis (-1,0) -> 180 degrees (PI)
        assertPolar(-1, 0, 1.0, Math.PI);

        // 5. Test Negative Y-axis (0,-1) -> -90 degrees (-PI/2)
        assertPolar(0, -1, 1.0, -Math.PI / 2);

        // 6. Test Quadrant 1 (1,1) -> 45 degrees (PI/4)
        assertPolar(1, 1, Math.sqrt(2), Math.PI / 4);

        // 7. Test Quadrant 2 (-1, 1) -> 135 degrees (3PI/4)
        assertPolar(-1, 1, Math.sqrt(2), 3 * Math.PI / 4);
    }

    /**
     * Helper to verify Cartesian to Polar conversion
     */
    private void assertPolar(double x, double y, double expectedR, double expectedPh) {
        Point2D p = new Point2D(x, y);
        PolarPoint result = p.toPolar2d(p);

        assertEquals(expectedR, result.getR(), THRESHOLD, "Radius mismatch at (" + x + "," + y + ")");
        assertEquals(expectedPh, result.getPh(), THRESHOLD, "Angle mismatch at (" + x + "," + y + ")");
    }



    @Test
    void testPointPP2d() {
        // 1. Test met eenvoudige positieve getallen
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(10.0, 10.0);
        Point2D result = Points2D.pointPP2d(p1, p2);

        assertEquals(5.0, result.getX(), DELTA);
        assertEquals(5.0, result.getY(), DELTA);

        // 2. Test met negatieve getallen (midden in de oorsprong)
        Point2D p3 = new Point2D(-5.0, -5.0);
        Point2D p4 = new Point2D(5.0, 5.0);
        Point2D resultOrigin = Points2D.pointPP2d(p3, p4);

        assertEquals(0.0, resultOrigin.getX(), DELTA);
        assertEquals(0.0, resultOrigin.getY(), DELTA);

        // 3. Test met asymmetrische punten
        Point2D p5 = new Point2D(1.0, 2.0);
        Point2D p6 = new Point2D(4.0, 6.0);
        Point2D resultAsym = Points2D .pointPP2d(p5, p6);

        assertEquals(2.5, resultAsym.getX(), DELTA); // (1+4)/2
        assertEquals(4.0, resultAsym.getY(), DELTA); // (2+6)/2
    }

    @Test
    void showPoint(){
        Point2D p1 = new Point2D(0.0, 0.0);

        System.out.println("the text of the point"+ p1.toString());

        assertEquals("[x,y]= 0.000000, 0.000000",p1.toString());
    }


}
