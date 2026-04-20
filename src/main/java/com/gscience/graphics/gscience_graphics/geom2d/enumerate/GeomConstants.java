package com.gscience.graphics.gscience_graphics.geom2d.enumerate;

public final class GeomConstants {

    // Private constructor prevents instantiation
    private GeomConstants() {}

    /** * Epsilon values for floating point precision checks.
     * Use EPS6 for standard coordinate comparisons.
     * Use EPS9 for strict angular or collinearity checks.
     */
    public static final double EPS2 = 1e-2;
    public static final double EPS4 = 1e-4;
    public static final double EPS6 = 1e-6;
    public static final double EPS8 = 1e-8;
    public static final double EPS9 = 1e-9;

    /** * Infinity substitute for geometric calculations where
     * Double.POSITIVE_INFINITY might cause overflow or NaN issues.
     */
    public static final double INF = 1e8;

    /** * 2 * PI (Tau), calculated from Math.PI for maximum double precision.
     */
    public static final double PI2 = 2.0 * Math.PI;

    /**
     * Conversion factor: Degrees to Radians.
     */
    public static final double DEG_TO_RAD = Math.PI / 180.0;
}