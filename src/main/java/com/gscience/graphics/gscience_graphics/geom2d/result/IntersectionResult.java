package com.gscience.graphics.gscience_graphics.geom2d.result;

import com.gscience.graphics.gscience_graphics.geom2d.Point2D;
import com.gscience.graphics.gscience_graphics.geom2d.enumerate.IntersectionStatus;
import lombok.Data;

@Data
public class IntersectionResult {

    public final IntersectionStatus status; // 0: None, 1: Tangent, 2: Intersection
    public final Point2D p1;
    public final Point2D p2;

    public IntersectionResult(IntersectionStatus status, Point2D p1, Point2D p2) {
        this.status = status;
        this.p1 = p1;
        this.p2 = p2;
    }
}
