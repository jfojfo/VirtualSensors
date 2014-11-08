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
* 
* 
* 光照传感器
* 
* @author pengxiaolong
*
*/
public class TYPE_LIGHT extends Activity {
         
        private SensorManager sensorManager;
         
        private Sensor illuminationSensor;
         
        private TextView tv_g;
         
        private TextView tv_info;
 
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.light);
                View_init();
                pojectinit();
        }
 
        /**
         * 对象的初始化
         */
        private void pojectinit(){
                sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
                illuminationSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                if(illuminationSensor==null){
                        Toast.makeText(this, "您的设备沾不支持该功能！", 0).show();
                }else{        
	                String str="\n名字："+illuminationSensor.getName()+"\n电池："+illuminationSensor.getPower()+"\n类型："+illuminationSensor.getType()+"\nVendor:"
	                			+illuminationSensor.getVendor()+"\n版本："+illuminationSensor.getVersion()+"\n幅度："+illuminationSensor.getMaximumRange();
	                tv_info.setText(str);
                }
                sensorManager.registerListener(sensorEventListener, illuminationSensor, SensorManager.SENSOR_DELAY_NORMAL);
 
        }
         
        /**
         * view的初始化
         */
        private void View_init()
        {
                tv_g=(TextView)findViewById(R.id.tv_g);
                tv_info=(TextView)findViewById(R.id.info);
                 
                 
        }
         
         
         
        @Override
        protected void onDestroy() {
                // TODO Auto-generated method stub
                super.onDestroy();
                sensorManager.unregisterListener(sensorEventListener);
        }
         
         
        /**
         * 监听器
         */
        private SensorEventListener sensorEventListener=new SensorEventListener(){
                 
                @Override
                public void onSensorChanged(SensorEvent event) {
                        // TODO Auto-generated method stub
                        float[] values=event.values; 
                        tv_g.setText("光线强度为："+values[0]);       
                }
                 
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        // TODO Auto-generated method stub
                         
                }
        };
 
         
}