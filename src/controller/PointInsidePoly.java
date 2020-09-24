package controller;

public class PointInsidePoly {

    static int INF = 10000;

    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Given three colinear points p, q, r,
    // the function checks if point q lies
    // on line segment 'pr'
    static boolean onSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y)) {
            return true;
        }
        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    static int orientation(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x)
                - (q.x - p.x) * (r.y - q.y);

        if (val == 0) {
            return 0; // colinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // The function that returns true if
    // line segment 'p1q1' and 'p2q2' intersect.
    static boolean doIntersect(Point p1, Point q1,
                               Point p2, Point q2) {
        // Find the four orientations needed for
        // general and special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are colinear and
        // p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }

        // p1, q1 and p2 are colinear and
        // q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }

        // p2, q2 and p1 are colinear and
        // p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }

        // p2, q2 and q1 are colinear and
        // q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }

        // Doesn't fall in any of the above cases
        return false;
    }

    // Returns true if the point p lies
    // inside the polygon[] with n vertices
    static boolean isInside(Point polygon[], int n, Point p) {
        // There must be at least 3 vertices in polygon[]
        if (n < 3) {
            return false;
        }

        // Create a point for line segment from p to infinite
        Point extreme = new Point(INF, p.y);

        // Count intersections of the above line
        // with sides of polygon
        int count = 0, i = 0;
        do {
            int next = (i + 1) % n;

            // Check if the line segment from 'p' to
            // 'extreme' intersects with the line
            // segment from 'polygon[i]' to 'polygon[next]'
            if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                // If the point 'p' is colinear with line
                // segment 'i-next', then check if it lies
                // on segment. If it lies, return true, otherwise false
                if (orientation(polygon[i], p, polygon[next]) == 0) {
                    return onSegment(polygon[i], p,
                            polygon[next]);
                }

                count++;
            }
            i = next;
        } while (i != 0);

        // Return true if count is odd, false otherwise
        return (count % 2 == 1); // Same as (count%2 == 1)
    }

    // Driver Code
    public static void main(String[] args) {
        Point polygon1[] = {new Point(-33.03861, 150.67741),
                new Point(-33.22013, 150.67003),
                new Point(-33.5475, 150.6548),
                new Point(-33.51542, 150.60664),
                new Point(-33.51167, 150.57035),
                new Point(-33.49687, 150.51741),
                new Point(-33.49219, 150.44517),
                new Point(-33.50411, 150.37843),
                new Point( -33.32884, 150.0367),
                new Point( -33.45044, 149.96045),
                new Point( -33.51742, 149.95309),
                new Point( -33.70819, 150.02141),
                new Point( -33.78547, 150.6502),
                new Point( -33.7955, 150.64964),
                new Point( -33.81336, 150.65302),
                new Point( -33.82269, 150.65268),
                new Point(  -33.84192, 150.64928),
                new Point(  -34.06898, 150.43392),
                new Point(  -34.7447, 149.92655),
                new Point( -34.71719, 150.05908),
                new Point(  -34.65471, 150.30477),
                new Point(  -34.68189, 150.48879),
                new Point( -34.68303, 150.50467),
                new Point(  -34.59833, 150.66292),
                new Point(  -34.71302, 150.83615),
                new Point(  -34.66171, 150.85144),
                new Point(  -34.42435, 150.9001),
                new Point(  -34.3018, 150.9385),
                new Point(  -34.2409, 150.97967),
                new Point(  -34.17039, 151.05012),
                new Point(  -34.16546, 151.05331),
                new Point(  -34.10394, 151.09123),
                new Point(  -34.05171, 151.154520),
                 new Point(  -34.03857, 151.14524),
                 new Point(  -33.97969, 151.24492),
                 new Point(  -33.93786, 151.25767),
                 new Point(  -33.74782, 151.29065),
                 new Point(  -33.6908, 151.30529),
                 new Point(  -33.62645, 151.33383),
                 new Point(  -33.60626, 151.33515),
                 new Point(  -33.42579, 151.3997),
                 new Point(  -33.19043, 151.56775),
                 new Point(  -33.0711, 151.47783),
                 new Point(  -33.14803, 151.20478),
                 new Point(  -33.13864, 151.20207),
                 new Point(  -33.08839, 151.13098),
                 new Point(  -33.42808, 150.83018),
                 new Point(  -33.40857, 150.78629),
                 new Point(  -33.37315, 150.74096)};
        int n = polygon1.length;
//        Point p = new Point(-37.874831, 145.041638);
        Point p = new Point(-33.774210, 150.746492);
        if (isInside(polygon1, n, p)) {
            System.out.println("It's in the Isochrone");
        } else {
            System.out.println("It's not in the Isochrone");
        }
    }
}