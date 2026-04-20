package com.gscience.graphics.gscience_graphics.geom2d;

import com.gscience.graphics.gscience_graphics.geom2d.enumerate.IntersectionStatus;
import com.gscience.graphics.gscience_graphics.geom2d.exception.LineLineException;
import com.gscience.graphics.gscience_graphics.geom2d.result.IntersectionResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleLineIntersectionTest {

    @Test
    @DisplayName("Should find two intersection points for a line passing through the center")
    void testIntersectingLine() throws LineLineException {
        // Circle: Radius 5, Center (0,0)
        Circle2D circle = new Circle2D(5.0, new Point2D(0, 0));

        // Line: Horizontal line y=0 (Direction vector a=1, b=0; Anchor x=0, y=0)
        Line2D line = new Line2D(1.0, 0.0, 0.0, 0.0);

        IntersectionResult result = circle.pointOL2D(circle, line);

        assertEquals(IntersectionStatus.INTERSECT, result.status);
        // Points should be at (5,0) and (-5,0)
        assertNotNull(result.p1);
        assertNotNull(result.p2);

        // Use a small delta for floating point comparisons
        assertEquals(5.0, Math.abs(result.p1.getX()), 1e-9);
        assertEquals(0.0, result.p1.getY(), 1e-9);
        assertEquals(5.0, Math.abs(result.p2.getX()), 1e-9);
        assertEquals(0.0, result.p2.getY(), 1e-9);
    }

    @Test
    @DisplayName("Should find one intersection point for a tangent line")
    void testTangentLine() throws LineLineException {
        // Circle: Radius 5, Center (0,0)
        Circle2D circle = new Circle2D(5.0, new Point2D(0, 0));

        // Line: Vertical line x=5 (Direction vector a=0, b=1; Anchor x=5, y=0)
        Line2D line = new Line2D(0.0, 1.0, 5.0, 0.0);

        IntersectionResult result = circle.pointOL2D(circle, line);

        assertEquals(IntersectionStatus.TANGENT, result.status);
        // In the tangent case, p1 and p2 should be the same point (5,0)
        assertEquals(5.0, result.p1.getX(), 1e-9);
        assertEquals(0.0, result.p1.getY(), 1e-9);
    }

    @Test
    @DisplayName("Should return NO_INTERSECTION for a line outside the circle")
    void testNoIntersection() throws LineLineException {
        // Circle: Radius 5, Center (0,0)
        Circle2D circle = new Circle2D(5.0, new Point2D(0, 0));

        // Line: Horizontal line y=10 (Direction vector a=1, b=0; Anchor x=0, y=10)
        Line2D line = new Line2D(1.0, 0.0, 0.0, 10.0);

        IntersectionResult result = circle.pointOL2D(circle, line);

        assertEquals(IntersectionStatus.NO_INTERSECTION, result.status);
        assertNull(result.p1);
        assertNull(result.p2);
    }

    @Test
    void testLineIntersectsCircleAtTwoPoints() throws LineLineException {
        // 1. Setup de cirkel (Middelpunt 0,0, Straal 5)
        Point2D center = new Point2D(0, 0);
        Circle2D circle = new Circle2D(5.0,center);

        // 2. Setup de lijn (Horizontaal op y=3)
        // De vector (1,0) is een eenheidsvector naar rechts
        Point2D lineOrigin = new Point2D(0, 3);
        Point2D lineDirection = new Point2D(1, 0);
        Line2D line = new Line2D(lineOrigin, lineDirection);

        // 3. Voer de berekening uit
        // (Zorg dat deze methode toegankelijk is, bijv. in een GeometryUtils klasse)
        IntersectionResult result = circle.pointOL2D(circle, line);

        // 4. Verifieer de status
        assertEquals(IntersectionStatus.INTERSECT, result.getStatus(),
                "De lijn moet de cirkel snijden op twee punten.");

        // 5. Verifieer de coördinaten
        // P1 verwachten we op (4, 3)
        assertNotNull(result.getP1());
        assertEquals(4.0, result.getP1().getX(), 1e-6);
        assertEquals(3.0, result.getP1().getY(), 1e-6);

        // P2 verwachten we op (-4, 3)
        assertNotNull(result.getP2());
        assertEquals(-4.0, result.getP2().getX(), 1e-6);
        assertEquals(3.0, result.getP2().getY(), 1e-6);
    }
}
