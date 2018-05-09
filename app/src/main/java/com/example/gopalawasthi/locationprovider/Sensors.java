package com.example.gopalawasthi.locationprovider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Sensors extends AppCompatActivity implements SensorEventListener{
    TextView x;
    TextView y;
    TextView z;
    SensorManager manager;
        Sensor sensor;
    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this,sensor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        x= findViewById(R.id.textView);
        y = findViewById(R.id.textView2);
        z = findViewById(R.id.textView3);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
      sensor =  manager.getDefaultSensor(Sensor.TYPE_LIGHT);
//       List<Sensor> sensorsList =manager.getSensorList(Sensor.TYPE_ALL);
//       Log.d("Sensors",sensorsList+"");
//       final Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//       if(accelerometer!= null){
//           manager.registerListener(new SensorEventListener() {
//               @Override
//               public void onSensorChanged(SensorEvent event) {
//                 float[] a = event.values;
//                 float x_axis = a[0];
//                 float y_axis = a[1];
//                 float z_axis = a[2];
//
//                 x.setText("x_axis" + x_axis);
//                 y.setText("y_axis" + y_axis);
//                 z.setText("z_axis"+ z_axis);
//               }
//
//               @Override
//               public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//
//               }
//           },accelerometer,3000000);
//
//       }else{
//           Toast.makeText(this, "no accelerometer found", Toast.LENGTH_SHORT).show();
//       }


    }
    boolean a = false;

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.values[0] > 60 && a==false ){
            a =true;
            Toast.makeText(this, ""+ event.values[0], Toast.LENGTH_SHORT).show();
           MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.groszek_mp3);
           mediaPlayer.start();
            Toast.makeText(this, "light is detected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "light sensor is not detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
