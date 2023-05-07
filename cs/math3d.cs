using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace math3d
{
    class Quaternion
    {
        private float x;
        private float y;
        private float z;
        private float w;
        public Quaternion()
        {

        }
        public Quaternion(float x, float y, float z, float w)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }
        public void SetX(float x)
        {
            this.x = x;
        }
        public void SetY(float y)
        {
            this.y = y;
        }
        public void SetZ(float z)
        {
            this.z = z;
        }
        public void SetW(float w)
        {
            this.w = w;
        }

        public float GetX()
        {
            return x;
        }
        public float GetY()
        {
            return y;
        }
        public float GetZ()
        {
            return z;
        }
        public float GetW()
        {
            return w;
        }
        public RotateMatrix Quaternion2RotateMatrix()
        {
            RotateMatrix rotateMatrix = new RotateMatrix();
            rotateMatrix.SetR11(1 - 2 * y * y - 2 * z * z);
            rotateMatrix.SetR12(2 * x * y + 2 * w * z);
            rotateMatrix.SetR13(2 * x * z + 2 * w * y);
            rotateMatrix.SetR21(2 * x * y + 2 * w * z);
            rotateMatrix.SetR22(1 - 2 * x * x - 2 * z * z);
            rotateMatrix.SetR23(2 * y * z - 2 * w * x);
            rotateMatrix.SetR31(2 * x * z - 2 * w * y);
            rotateMatrix.SetR32(2 * y * z + 2 * w * x);
            rotateMatrix.SetR33(1 - 2 * x * x - 2 * y * y);
            return rotateMatrix;
        }
        public EulerAngle Quaternion2EulerAngle()
        {
            RotateMatrix rotateMatrix = Quaternion2RotateMatrix();
     
            EulerAngle eulerAngle = new EulerAngle();
            eulerAngle.SetAngleX(360 / (2 * MathF.PI) * MathF.Asin(-rotateMatrix.GetR23()));
            eulerAngle.SetAngleY(360 / (2 * MathF.PI) * MathF.Atan2(rotateMatrix.GetR13(), rotateMatrix.GetR33()));
            eulerAngle.SetAngleZ(360 / (2 * MathF.PI) * MathF.Atan2(rotateMatrix.GetR21(), rotateMatrix.GetR22()));

            return eulerAngle;
        }
    }
    class RotateMatrix
    {
        private float r11;
        private float r12;
        private float r13;
        private float r21;
        private float r22;
        private float r23;
        private float r31;
        private float r32;
        private float r33;
        public void SetR11(float r11)
        {
            this.r11 = r11;
        }
        public void SetR12(float r12)
        {
            this.r12 = r12;
        }
        public void SetR13(float r13)
        {
            this.r13 = r13;
        }
        public void SetR21(float r21)
        {
            this.r21 = r21;
        }
        public void SetR22(float r22)
        {
            this.r22 = r22;
        }
        public void SetR23(float r23)
        {
            this.r23 = r23;
        }
        public void SetR31(float r31)
        {
            this.r31 = r31;
        }
        public void SetR32(float r32)
        {
            this.r32 = r32;
        }
        public void SetR33(float r33)
        {
            this.r33 = r33;
        }
        public float GetR11()
        {
            return r11;
        }
        public float GetR12()
        {
            return r12;
        }
        public float GetR13()
        {
            return r13;
        }
        public float GetR21()
        {
            return r21;
        }
        public float GetR22()
        {
            return r22;
        }
        public float GetR23()
        {
            return r23;
        }
        public float GetR31()
        {
            return r31;
        }
        public float GetR32()
        {
            return r32;
        }
        public float GetR33()
        {
            return r33;
        }
    }
    class Angle
    {
        private float rad;
        private float deg;

        public Angle()
        {

        }
        public Angle(float deg)
        {
            this.deg = deg;
            rad = deg * 2 * MathF.PI / 360; 
        }
        public void SetDeg(float deg)
        {
            this.deg = deg;
            rad = deg * 2 * MathF.PI / 360;
        }
        public float GetRad()
        {
            return rad;
        }
        public float GetDeg()
        {
            return deg;
        }
    }
    class EulerAngle
    {
        private Angle angleX;
        private Angle angleY;
        private Angle angleZ;
        public EulerAngle()
        {
            angleX = new Angle();
            angleY = new Angle();
            angleZ = new Angle();
        }
        public EulerAngle(float degX, float degY, float degZ)
        {
            angleX = new Angle(degX);
            angleY = new Angle(degY);
            angleZ = new Angle(degZ);
        }
        public void SetAngleX(float deg)
        {
            angleX.SetDeg(deg);
        }
        public void SetAngleY(float deg)
        {
            angleY.SetDeg(deg);
        }
        public void SetAngleZ(float deg)
        {
            angleZ.SetDeg(deg);
        }
        public Angle GetAngleX()
        {
            return angleX;
        }
        public Angle GetAngleY()
        {
            return angleY;
        }
        public Angle GetAngleZ()
        {
            return angleZ;
        }
        public RotateMatrix EulerAngle2RotateMatrix()
        {
            RotateMatrix rotateMatrix = new RotateMatrix();
            float x = angleX.GetRad();
            float y = angleY.GetRad();
            float z = angleZ.GetRad();
            rotateMatrix.SetR11(MathF.Cos(y) * MathF.Cos(z) + MathF.Sin(y) * MathF.Sin(x) * MathF.Sin(z));
            rotateMatrix.SetR12(-MathF.Cos(y) * MathF.Sin(z) + MathF.Sin(y) * MathF.Sin(x) * MathF.Cos(z));
            rotateMatrix.SetR13(MathF.Sin(y) * MathF.Cos(x));
            rotateMatrix.SetR21(MathF.Cos(x) * MathF.Sin(z));
            rotateMatrix.SetR22(MathF.Cos(x) * MathF.Cos(z));
            rotateMatrix.SetR23(-MathF.Sin(x));
            rotateMatrix.SetR31(-MathF.Sin(y) * MathF.Cos(z) + MathF.Cos(y) * MathF.Sin(x) * MathF.Sin(z));
            rotateMatrix.SetR32(MathF.Sin(y) * MathF.Sin(z) + MathF.Cos(y) * MathF.Sin(x) * MathF.Cos(z));
            rotateMatrix.SetR33(MathF.Cos(y) * MathF.Cos(x));
            return rotateMatrix;
        }
        public Quaternion EulerAngle2Quaternion()
        {
            float x = angleX.GetRad();
            float y = angleY.GetRad();
            float z = angleZ.GetRad();
            Quaternion q = new Quaternion();
            q.SetX(MathF.Cos(y / 2) * MathF.Sin(x / 2) * MathF.Cos(z / 2) + MathF.Cos(x / 2) * MathF.Sin(y / 2) * MathF.Sin(z / 2));
            q.SetY(MathF.Sin(y / 2) * MathF.Cos(x / 2) * MathF.Cos(z / 2) - MathF.Sin(x / 2) * MathF.Cos(y / 2) * MathF.Sin(z / 2));
            q.SetZ(MathF.Cos(y / 2) * MathF.Cos(x / 2) * MathF.Sin(z / 2) - MathF.Sin(x / 2) * MathF.Sin(y / 2) * MathF.Cos(z / 2));
            q.SetW(MathF.Cos(y/2)* MathF.Cos(x / 2)* MathF.Cos(z / 2)+ MathF.Sin(x / 2)* MathF.Sin(y / 2)* MathF.Sin(z / 2));
            return q;
        }
    }
}
