package math3d.gjk2d;

import math3d.primary.Vector3;

import java.util.ArrayList;
import java.util.List;

public class AreaRectangle {
    private final Vector3 center;
    private final float xWidth;
    private final float zWidth;
    private final List<Vector3> vertex;

    public AreaRectangle(Vector3 center, float xWidth, float zWidth) {
        this.center = center;
        this.xWidth = xWidth;
        this.zWidth = zWidth;
        vertex = getVertex();
    }

    private List<Vector3> getVertex() {
        List<Vector3> vertex = new ArrayList<>();
        Vector3 point0 = new Vector3(center.x - xWidth / 2, center.y, center.z + zWidth / 2);
        Vector3 point1 = new Vector3(center.x + xWidth / 2, center.y, center.z + zWidth / 2);
        Vector3 point2 = new Vector3(center.x - xWidth / 2, center.y, center.z - zWidth / 2);
        Vector3 point3 = new Vector3(center.x + xWidth / 2, center.y, center.z - zWidth / 2);
        vertex.add(point0);
        vertex.add(point1);
        vertex.add(point2);
        vertex.add(point3);
        return vertex;
    }

    public boolean isInArea(Vector3 point) {
        List<Vector3> pointList = new ArrayList<>();
        pointList.add(point);
        return new AreaRectangleAlgorithm(vertex, pointList).isInAreaConvex();
    }

    public boolean isInArea(Vector3 center, float radius) {
        return new AreaRectangleAlgorithm(center, radius, vertex).isInAreaCircle();
    }

    public boolean isInArea(List<Vector3> otherVertex) {

        return new AreaRectangleAlgorithm(vertex, otherVertex).isInAreaConvex();

    }
}

