
#include <iostream>
#include "Eigen/core"
#include "Eigen/Dense"

using namespace std;

#define PI 3.1415926

//Find the direction
Eigen::AngleAxisf test01(Eigen::Quaternionf tPose, Eigen::Quaternionf aPose) {
	//Calculate
	Eigen::Quaternionf tPose_conjugate;
	Eigen::Quaternionf target_quaternion;
	Eigen::AngleAxisf target_AngleAxis;
	double axis[3];
	tPose_conjugate = tPose.conjugate();
	target_quaternion = aPose * tPose_conjugate;
	target_AngleAxis = target_quaternion;
	axis[0] = target_AngleAxis.axis().x();
	axis[1] = target_AngleAxis.axis().y();
	axis[2] = target_AngleAxis.axis().z();
	//cout << "(" << axis[0] <<"," << axis[1] << "," << axis[2] << ")" << endl;
	return target_AngleAxis;
}

//Find theta
//theta [0, pi/2]
//逆时针为正 顺时针为负
//Principal arc cosine of x, in the interval [0,pi] radians.
float test02(Eigen::AngleAxisf target_AngleAxis) {
	float theta;
	Eigen::Vector2f y_axis(0,1);
	Eigen::Vector2f target_axis(target_AngleAxis.axis().x(), target_AngleAxis.axis().y());
	theta = y_axis.transpose() * target_axis;
	theta=theta/(sqrt(y_axis.transpose() * y_axis) * sqrt(target_axis.transpose() * target_axis));
	theta = acos(theta)*180/PI;

	if (theta > 90) {
		theta = 180 - theta;
	}

	if (target_axis.x() * target_axis.y() > 0) {
		theta = -theta;
	}
	
	cout<< theta <<endl;
	return theta;
}

int main()
{
	Eigen::AngleAxisf target_AngleAxis;
	float theta;
	//Input quaternion of tPose and aPose
	//Input format q(w,x,y,z)
	//test1
	//Eigen::Quaternionf tPose(1, 0, 0, 0);
	//Eigen::Quaternionf aPose(0.707, 0, -0.707, 0);
	//test2
	Eigen::Quaternionf tPose(0.707, 0, 0, -0.707);
	Eigen::Quaternionf aPose(0.5, -0.5, -0.5, -0.5);

	target_AngleAxis = test01(tPose, aPose);
	theta = test02(target_AngleAxis);
}