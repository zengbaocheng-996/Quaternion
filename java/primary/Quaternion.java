package math3d.primary;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Quaternion {
    private float w;
    private float x;
    private float y;
    private float z;

    public Quaternion() {
    }

    public Quaternion(Vector3 vector) {
        this.w = 0;
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion multiplication(Quaternion other) {
        return new Quaternion(
                this.w * other.w - this.x * other.x - this.y * other.y - this.z * other.z,
                this.w * other.x + this.x * other.w + this.y * other.z - this.z * other.y,
                this.w * other.y - this.x * other.z + this.y * other.w + this.z * other.x,
                this.w * other.z + this.x * other.y - this.y * other.x + this.z * other.w);
    }

    public Vector3 getVector3() {
        return new Vector3(this.x, this.y, this.z);
    }

    public Quaternion Euler2Quaternion(Vector3 euler) {
        return new Quaternion(
                (float) (cos(euler.x / 2) * cos(euler.y / 2) * cos(euler.z / 2) + sin(euler.x / 2) * sin(euler.y / 2) * sin(euler.z / 2)),
                (float) (sin(euler.x / 2) * cos(euler.y / 2) * cos(euler.z / 2) - cos(euler.x / 2) * sin(euler.y / 2) * sin(euler.z / 2)),
                (float) (cos(euler.x / 2) * sin(euler.y / 2) * cos(euler.z / 2) + sin(euler.x / 2) * cos(euler.y / 2) * sin(euler.z / 2)),
                (float) (cos(euler.x / 2) * cos(euler.y / 2) * sin(euler.z / 2) - sin(euler.x / 2) * sin(euler.y / 2) * cos(euler.z / 2))
        );
    }

    public Quaternion getConjugate() {
        return new Quaternion(this.w, -this.x, -this.y, -this.z);
    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}