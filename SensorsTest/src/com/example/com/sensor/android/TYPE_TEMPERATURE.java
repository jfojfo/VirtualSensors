package com.example.com.sensor.android;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

/**
* 温度传感器
* 
* @author pengxiaolong
* 
*/
public class TYPE_TEMPERATURE extends Activity {

        private SensorManager sensorManager;

        private Sensor tempertureSensor;

        private TextView tv_w;

        private TextView tv_info;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.temperature);

                tv_w = (TextView) this.findViewById(R.id.tv_w);
                tv_info = (TextView) this.findViewById(R.id.info);

                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

                tempertureSensor = sensorManager
                                .getDefaultSensor(Sensor.TYPE_TEMPERATURE);
                if (tempertureSensor == null) {
                        Toast.makeText(this, "你的设备不支持该功能", 0).show();
                        finish();
                } else {

                        String str = "\n名字：" + tempertureSensor.getName() + "\n电池："
                                        + tempertureSensor.getPower() + "\n类型："
                                        + tempertureSensor.getType() + "\nVendor:"
                                        + tempertureSensor.getVendor() + "\n版本："
                                        + tempertureSensor.getVersion() + "\n幅度："
                                        + tempertureSensor.getMaximumRange();

                        tv_info.setText(str);
                }
                sensorManager.registerListener(sensorEventListener, tempertureSensor,
                                SensorManager.SENSOR_DELAY_NORMAL);

        }

      
        private SensorEventListener sensorEventListener = new SensorEventListener() {

                @Override
                public void onSensorChanged(SensorEvent event) {
                        // TODO Auto-generated method stub
                        float[] values = event.values;
                        tv_w.setText("温度为：" + values[0]);

                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        // TODO Auto-generated method stub

                }
        };

        @Override
        protected void onDestroy() {
                // TODO Auto-generated method stub
                super.onDestroy();
                sensorManager.unregisterListener(sensorEventListener);
        }

}