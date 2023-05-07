using math3d;
using System;

class Program
{
    static void Main()
    {
        Console.WriteLine("");

        //EulerAngle eulerAngle =new EulerAngle(40, 50, 60);
        EulerAngle eulerAngle =new EulerAngle(60, 50, 40);
        Quaternion quaternion=eulerAngle.EulerAngle2Quaternion();
        Console.WriteLine("欧拉角->四元数");
        Console.WriteLine(quaternion.GetX());
        Console.WriteLine(quaternion.GetY());
        Console.WriteLine(quaternion.GetZ());
        Console.WriteLine(quaternion.GetW());
        Console.WriteLine("");

        Console.WriteLine("角度->弧度");
        Console.WriteLine(eulerAngle.GetAngleX().GetRad());
        Console.WriteLine(eulerAngle.GetAngleY().GetRad());
        Console.WriteLine(eulerAngle.GetAngleZ().GetRad());
        Console.WriteLine("");

        //Quaternion quaternion1 = new Quaternion((float)0.5, (float)0.2, (float)0.3, (float)0.8);
        Quaternion quaternion1 = new Quaternion((float)0.6, (float)0.2, (float)0.1, (float)0.8);
        EulerAngle eulerAngle1 = quaternion1.Quaternion2EulerAngle();
        Console.WriteLine("四元数->欧拉角");
        Console.WriteLine(eulerAngle1.GetAngleX().GetDeg());
        Console.WriteLine(eulerAngle1.GetAngleY().GetDeg());
        Console.WriteLine(eulerAngle1.GetAngleZ().GetDeg());

        Console.ReadLine();
    }
}