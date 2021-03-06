package com.example.com.sensor.android;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
 
/**
* 姿态传感器
* 
* @author  
*
*/
public class TYPE_ORIENTATION extends Activity {
 
         
         SensorManager sensorManager;//管理器对象
         
         Sensor accSensor;//传感器对象
         
         TextView tv_X;
         
         TextView tv_Y;
         
         TextView tv_Z;
         
         TextView tv_info;
         
         
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.orientation);
                View_init();
                projectinit();
        }
 

         
         
        /**
         * 对象的初始化
         */
        private void projectinit(){
                 
                sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
                accSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
                if(accSensor==null){
                        Toast.makeText(this, "您的设备不支持该功能！", 0).show();
                        finish();
                }else{
                String str="\n名字："+accSensor.getName()+"\n电池："+accSensor.getPower()+"\n类型："+accSensor.getType()+"\nVendor:"+accSensor.getVendor()+"\n版本："+accSensor.getVersion()+"\n幅度："+accSensor.getMaximumRange();
             
                tv_info.setText(str);
                }
                 
                /**
                 * 注册监听器
                 */
                sensorManager.registerListener(sensoreventlistener, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
 
        }
         
        /**
         * 小组件的初始化
         */
        private void View_init(){
                 
                tv_info=(TextView)this.findViewById(R.id.info);
                tv_X=(TextView)findViewById(R.id.tvX);
                tv_Y=(TextView)findViewById(R.id.tvY);
                tv_Z=(TextView)findViewById(R.id.tvZ);
 
                 
        }
 
 
     
 
         
        @Override
        protected void onDestroy() {
                // TODO Auto-generated method stub
                super.onDestroy();
                /**
                 * 解除注册
                 */
                sensorManager.unregisterListener(sensoreventlistener);
 
        }
 
 
 
        private Handler handler=new Handler(){
 
                @Override
                public void handleMessage(Message msg) {
                        // TODO Auto-generated method stub
                         
                        switch(msg.what){
                         
                        case CHANGE:
                                 
                                float[] valuse=(float[]) msg.obj;
                                tv_X.setText("手机沿Yaw轴转过的角度为："+Float.toString(valuse[0]));
                                tv_Y.setText("手机沿Pitch轴转过的角度为："+Float.toString(valuse[1]));
                                tv_Z.setText("手机沿Roll轴转过的角度为："+Float.toString(valuse[2]));
                                break;
                        }
                        super.handleMessage(msg);
                }
                 
                 
                 
        };
        /**
         * 传感器的监听
         */
        private SensorEventListener sensoreventlistener=new SensorEventListener() {
                 
                @Override
                public void onSensorChanged(SensorEvent event) {
                        // TODO Auto-generated method stub
                         
                        float[] valuse=event.values;
                        Message message=new Message();
                        message.obj=valuse;
                        message.what=CHANGE;
                        handler.sendMessage(message);
                 
 
                }
                 
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        // TODO Auto-generated method stub
                         
                         
                }
        };
         
        private static final byte CHANGE=0x1;
 
}