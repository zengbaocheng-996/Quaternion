package math3d.rotate;

import math3d.primary.Quaternion;
import math3d.primary.Vector3;

public class rotate {
    public Vector3 RotatePointAroundPivot(Vector3 point, Vector3 pivot, Vector3 euler) {
        Vector3 direction = point.Sub2(pivot);
//        System.out.println(direction);
        Quaternion euler2Quaternion = new Quaternion().Euler2Quaternion(euler);
//        System.out.println(euler2Quaternion);
        Vector3 rotatedDirection = euler2Quaternion.multiplication(new Quaternion(direction)).multiplication(euler2Quaternion.getConjugate()).getVector3();
//        System.out.println(rotatedDirection);
        return rotatedDirection.Add2(pivot);
    }
}
