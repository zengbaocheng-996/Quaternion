package math3d.primary;

import static java.lang.Math.sqrt;

public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3() {
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 Sub2(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 Add2(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public float Dot2(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public void NormalizeXZ() {
        if (this.x * this.x + this.z * this.z == 0) {
            return;
        }
        this.x = this.x / (float) sqrt(this.x * this.x + this.z * this.z);
        this.z = this.z / (float) sqrt(this.x * this.x + this.z * this.z);
    }

    public Vector3 Cross(Vector3 other) {
        return new Vector3(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
    }

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
