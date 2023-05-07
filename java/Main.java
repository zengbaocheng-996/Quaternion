package math3d;

import math3d.gjk2d.AreaRectangle;
import math3d.primary.Vector3;
import math3d.rotate.rotate;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        test01();
        test02();
    }

    public static void test01() {
        System.out.println("旋转测试");
        Vector3 point = new Vector3(10, 0, 0);
        Vector3 pivot = new Vector3(0, 0, 0);
        Vector3 euler = new Vector3((float) Math.toRadians(360), 0, 0);
        System.out.println("旋转前的点：" + point);
        System.out.println("旋转后的点：" + new rotate().RotatePointAroundPivot(point, pivot, euler));
    }

    public static void test02() {
        System.out.println("碰撞检测");
        AreaRectangle rectangle = new AreaRectangle(new Vector3(0, 0, 10), 8, 6);
        //*********************点与矩形**********************
        if (rectangle.isInArea(new Vector3(0, 0, 5.9F))) {
            System.out.println("点与矩形：相交");
        } else {
            System.out.println("点与矩形：不相交");
        }
        //*******************凸多边形与矩形*******************
        List<Vector3> vertex = new ArrayList<>();
        Vector3 point21 = new Vector3(7F, 0, 1);
        Vector3 point22 = new Vector3(6F, 0, 2);
        Vector3 point23 = new Vector3(1, 0, 1);
        Vector3 point24 = new Vector3(1, 0, 2);
        Vector3 point25 = new Vector3(3.5F, 0, 3);
        vertex.add(point21);
        vertex.add(point22);
        vertex.add(point23);
        vertex.add(point24);
        vertex.add(point25);
        if (rectangle.isInArea(vertex)) {
            System.out.println("凸多边形与矩形：相交");
        } else {
            System.out.println("凸多边形与矩形：不相交");
        }
        //********************圆形与矩形*********************
        if (rectangle.isInArea(new Vector3(0, 0, 10), 100F)) {
            System.out.println("圆形与矩形：相交");
        } else {
            System.out.println("圆形与矩形：不相交");
        }
    }
}
