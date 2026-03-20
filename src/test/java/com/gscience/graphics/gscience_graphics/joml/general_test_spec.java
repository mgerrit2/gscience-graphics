package com.gscience.graphics.gscience_graphics.joml;

import org.joml.Vector2f;
import org.junit.jupiter.api.Test;

class general_test_spec {

    @Test
    void distance_bewtween2Points() {

        Vector2f pointA = new Vector2f(10.0f, 20.0f);
        Vector2f pointB = new Vector2f(13.0f, 24.0f);

        // Standard Euclidean distance
        float dist = pointA.distance(pointB);

    }

    @Test
    void eenheidsAngle_Between_to_vactors() {

        Vector2f line1Dir = new Vector2f(1, 0); // Horizontal
        Vector2f line2Dir = new Vector2f(1, 1); // 45 degrees

// Returns the angle in radians
        float angleInRadians = line1Dir.angle(line2Dir);

// Convert to degrees if needed
        float angleInDegrees = (float) Math.toDegrees(angleInRadians); // Result: 45.0

    }
}
