package com.example.com.sensor.android;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
* 传感器总汇
* 
* @author pengxiaolng
* 
*         #define SENSOR_TYPE_ACCELEROMETER 1
* 
*         加速度
* 
*         #define SENSOR_TYPE_MAGNETIC_FIELD 2
* 
*         磁力
* 
*         #define SENSOR_TYPE_ORIENTATION 3
* 
*         方向
* 
*         #define SENSOR_TYPE_GYROSCOPE 4
* 
*         陀螺仪
* 
*         #define SENSOR_TYPE_LIGHT 5
* 
*         光线感应
* 
*         #define SENSOR_TYPE_PRESSURE 6
* 
*         压力
* 
*         #define SENSOR_TYPE_TEMPERATURE 7
* 
*         温度
* 
*         #define SENSOR_TYPE_PROXIMITY 8
* 
*         距离
* 
*         #define SENSOR_TYPE_GRAVITY 9
* 
*         重力
* 
*         #define SENSOR_TYPE_LINEAR_ACCELERATION 10
* 
*         线性加速度
* 
*         #define SENSOR_TYPE_ROTATION_VECTOR 11
* 
*         旋转矢量
* 
*         #define Sensor_TYPE_RELATIVE_HUMIDITY 12 
*         
*         相对湿度
* 
*         传感器 类型 说明 常见用途 TYPE_ACCELEROMETER 硬件
*         测量施于设备的物理三维方向上（x、y和z轴）的加速度，包括重力，单位为m/s2。 运动检测（晃动、倾斜等）
*         TYPE_AMBIENT_TEMPERATURE 硬件 测量周围环境的温度，单位为摄氏度（°C）。参见下文。 监测气温
*         TYPE_GRAVITY 软件或硬件 测量施于设备的物理三维方向上（x、y和z轴）的重力加速度，单位为m/s2 。
*         运动检测（晃动、倾斜等） TYPE_GYROSCOPE 硬件 测量设备围绕每个物理三维方向（x、y和z轴）的转动角速度，单位为rad/s
*         。 转动检测（旋转、转动等） TYPE_LIGHT 硬件 测量周围环境的光照强度（照度），单位为lx。 控制屏幕亮度
*         TYPE_LINEAR_ACCELERATION 软件或硬件
*         测量施于设备的物理三维方向上（x、y和z轴）的加速度，但不包括重力，单位为m/s2。 监测某一维轴线上的加速度
*         TYPE_MAGNETIC_FIELD 硬件 测量周围物理三维方向（x、y和z轴）的地球磁场，单位为μT。 创建指南针
*         TYPE_ORIENTATION 软件 测量围绕物理三维方向（x、y和z轴）的旋转角度。自API level
*         3开始，利用重力传感器和地磁传感器，你可以用 getRotationMatrix() 方法读取倾角矩阵和旋转矩阵。 检测设备的方位
*         TYPE_PRESSURE 硬件 测量周围大气压力，单位为hPa或mbar。 监测气压的变化 TYPE_PROXIMITY 硬件
*         测量附近的物体与设备屏幕间的距离，单位为cm。此传感器的典型应用，是可以检测手持设备是否被人拿起来并靠近耳朵。 通话时确定电话的位置
*         TYPE_RELATIVE_HUMIDITY 硬件 测量周围环境的相对湿度，单位为百分比(%)。 监测结露点、绝对湿度和相对湿度。
*         TYPE_ROTATION_VECTOR 软件或硬件 根据设备旋转向量的三个参数测量设备的方向。 运动检测和转动检测
*         TYPE_TEMPERATURE 硬件 测量设备的温度，单位是摄氏度（°C）。这个传感器的实现因设备的差异而各不相同，并自API
*         Level 14开始由 TYPE_AMBIENT_TEMPERATURE 代替。
* 
*/
public class MainActivity extends Activity {

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main);

                // 准备显示信息的UI组建
                final TextView tx1 = (TextView) findViewById(R.id.TextView);

                // 从系统服务中获得传感器管理器
                SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

                // 从传感器管理器中获得全部的传感器列表
                List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);

                // 显示有多少个传感器
                tx1.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");

                // 显示每个传感器的具体信息
                for (Sensor s : allSensors) {
                	String tempString ="\n";
//                        String tempString = "\n" + "  设备名称：" + s.getName() + "\n"
//                                        + "  设备版本：" + s.getVersion() + "\n" + "  供应商："
//                                        + s.getVendor() + "\n";

                        switch (s.getType()) {
                        case Sensor.TYPE_ACCELEROMETER:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 加速度传感器accelerometer" + tempString);
                                break;
                        case Sensor.TYPE_GYROSCOPE:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 陀螺仪传感器gyroscope" + tempString);
                                break;
                        case Sensor.TYPE_LIGHT:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 环境光线传感器light" + tempString);
                                break;
                        case Sensor.TYPE_MAGNETIC_FIELD:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 电磁场传感器magnetic field" + tempString);
                                break;
                        case Sensor.TYPE_ORIENTATION:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 方向传感器orientation" + tempString);
                                break;
                        case Sensor.TYPE_PRESSURE:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 压力传感器pressure" + tempString);
                                break;
                        case Sensor.TYPE_PROXIMITY:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 距离传感器proximity" + tempString);
                                break;
                        case Sensor.TYPE_TEMPERATURE:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 温度传感器temperature" + tempString);
                                break;
                        case Sensor.TYPE_GRAVITY:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 重力传感器gravity" + tempString);
                                break;
                        case Sensor.TYPE_LINEAR_ACCELERATION:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 线性加速度传感器linearacceleration" + tempString);
                                break;
                        case Sensor.TYPE_ROTATION_VECTOR:
                                tx1.setText(tx1.getText().toString() + s.getType()
                                                + " 旋转矢量传感器rotation_vector" + tempString);
                                break;
//                        case Sensor.TYPE_RELATIVE_HUMIDITY:
//                                tx1.setText(tx1.getText().toString() + s.getType()
//                                                + " 周围环境相对湿度传感器relative_humidity" + tempString);
//                                break;
                        default:
                                tx1.setText(tx1.getText().toString() + s.getType() + " 未知传感器"
                                                + tempString);
                                break;
                        }
                }

        }
   public void lightclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_LIGHT.class);
		startActivity(intent);
	}
	public void orientationclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_ORIENTATION.class);
		startActivity(intent);
	}
	public void proximityclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_PROXIMITY.class);
		startActivity(intent);
	}
	public void magneticclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_MAGNETIC_FIELD.class);
		startActivity(intent);
	}
	public void acceleroclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_ACCELEROMETER.class);
		startActivity(intent);
	}
	public void gyroscopeclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_GYROSCOPE.class);
		startActivity(intent);
	}
	public void  temperclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_TEMPERATURE.class);
		startActivity(intent);
	}
	public void  lineraccclick(View v) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, TYPE_LINEAR_ACCELERATION.class);
		startActivity(intent);
	}
}