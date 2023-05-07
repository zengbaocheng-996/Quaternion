package math3d.gjk2d;

import math3d.primary.Vector3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AreaRectangleAlgorithm {
    private final Vector3 v;
    private final float radius;
    private final List<Vector3> vertexA;
    private final List<Vector3> vertexB;
    private final Vector3 origin;
    private Vector3 d;
    private List<Vector3> simplex;
    private final DetectedObject flag;

    enum DetectedObject {
        POINT, CONVEX, CIRCLE
    }

    public AreaRectangleAlgorithm(Vector3 v, float radius, List<Vector3> vertexA) {
        flag = DetectedObject.CIRCLE;
        this.v = v;
        this.radius = radius;
        this.vertexA = vertexA;
        origin = new Vector3(0, 0, 0);
        d = new Vector3();
        simplex = new ArrayList<>();
        vertexB = new ArrayList<>();
    }

    public AreaRectangleAlgorithm(List<Vector3> vertexA, List<Vector3> vertexB) {
        if (vertexB.size() > 1) {
            flag = DetectedObject.CONVEX;
        } else {
            flag = DetectedObject.POINT;
        }
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        origin = new Vector3(0, 0, 0);
        d = new Vector3();
        simplex = new ArrayList<>();
        v = new Vector3();
        radius = 0;
    }

    public boolean isInAreaCircle() {
        AreaRectangleAlgorithm algorithm = new AreaRectangleAlgorithm(this.v, this.radius, this.vertexA);
        algorithm.d.x = 1;
        algorithm.d.y = 0;
        algorithm.d.z = 0;
        algorithm.simplex.add(support(algorithm));
        algorithm.d = algorithm.origin.Sub2(algorithm.simplex.get(0));
        while (true) {
            Vector3 A = support(algorithm);
            if (A.Dot2(algorithm.d) < 0) {
                return false;
            }
            algorithm.simplex.add(A);
            if (handleSimplex(algorithm)) {
                return true;
            }
        }
    }

    public boolean isInAreaConvex() {
        AreaRectangleAlgorithm algorithm = new AreaRectangleAlgorithm(this.vertexA, this.vertexB);
        algorithm.d.x = 1;
        algorithm.d.y = 0;
        algorithm.d.z = 0;
        algorithm.simplex.add(support(algorithm));
        algorithm.d = algorithm.origin.Sub2(algorithm.simplex.get(0));
        while (true) {
            Vector3 A = support(algorithm);
            if (A.Dot2(algorithm.d) < 0) {
                return false;
            }
            algorithm.simplex.add(A);
            if (handleSimplex(algorithm)) {
                return true;
            }
        }
    }

    // m+n
    private Vector3 support(AreaRectangleAlgorithm algorithm) {
        if (flag == DetectedObject.CIRCLE) {
            List<Float> dotA = new ArrayList<>();
            for (Vector3 pointA : algorithm.vertexA) {
                pointA.y = 0;
                dotA.add(pointA.Dot2(algorithm.d));
            }
            int indexA = dotA.indexOf(Collections.max(dotA));
            reverseDirection(algorithm);
            algorithm.d.x = algorithm.d.x * radius;
            algorithm.d.z = algorithm.d.z * radius;
            Vector3 suspectB = v.Add2(algorithm.d);
            algorithm.d.NormalizeXZ();
            reverseDirection(algorithm);
            return algorithm.vertexA.get(indexA).Sub2(suspectB);
        } else if (flag == DetectedObject.CONVEX || flag == DetectedObject.POINT) {
            List<Float> dotA = new ArrayList<>();
            List<Float> dotB = new ArrayList<>();
            for (Vector3 pointA : algorithm.vertexA) {
                pointA.y = 0;
                dotA.add(pointA.Dot2(algorithm.d));
            }
            int indexA = dotA.indexOf(Collections.max(dotA));
            reverseDirection(algorithm);
            for (Vector3 pointB : algorithm.vertexB) {
                pointB.y = 0;
                dotB.add(pointB.Dot2(algorithm.d));
            }
            int indexB = dotB.indexOf(Collections.max(dotB));
            reverseDirection(algorithm);

            return algorithm.vertexA.get(indexA).Sub2(algorithm.vertexB.get(indexB));
        } else {
            return new Vector3();
        }
    }

    private static void reverseDirection(AreaRectangleAlgorithm algorithm) {
        algorithm.d.x = -algorithm.d.x;
        algorithm.d.y = 0;
        algorithm.d.z = -algorithm.d.z;
        algorithm.d.NormalizeXZ();
    }

    private static boolean handleSimplex(AreaRectangleAlgorithm algorithm) {
        if (algorithm.simplex.size() == 2) {
            return lineCase(algorithm);
        } else {
            return triangleCase(algorithm);
        }
    }

    private static boolean lineCase(AreaRectangleAlgorithm algorithm) {
        Vector3 A = algorithm.simplex.get(0);
        Vector3 B = algorithm.simplex.get(1);
        A.y = 0;
        B.y = 0;
        Vector3 AB = B.Sub2(A);
        Vector3 AO = algorithm.origin.Sub2(A);
        algorithm.d = AB.Cross(AO).Cross(AB);
        algorithm.d.NormalizeXZ();
        return false;
    }

    private static boolean triangleCase(AreaRectangleAlgorithm algorithm) {
        Vector3 A = algorithm.simplex.get(0);
        Vector3 B = algorithm.simplex.get(1);
        Vector3 C = algorithm.simplex.get(2);
        Vector3 CB = B.Sub2(C);
        Vector3 CA = A.Sub2(C);
        Vector3 CO = algorithm.origin.Sub2(C);
        Vector3 CBperp = CA.Cross(CB).Cross(CB);
        Vector3 CAperp = CB.Cross(CA).Cross(CA);
        if (CBperp.Dot2(CO) > 0) {
            algorithm.simplex.remove(0);
            algorithm.d = CBperp;
            return false;
        } else if (CAperp.Dot2(CO) > 0) {
            algorithm.simplex.remove(1);
            algorithm.d = CAperp;
            return false;
        }
        return true;
    }
}
