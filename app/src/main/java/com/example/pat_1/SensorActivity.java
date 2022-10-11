package com.example.pat_1;


import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    public Sensor envSense,temp,light, hum;
    float sensorValue;
    int currType;

    public static RepoStorage repo = new RepoStorage();
    boolean ext_detected;
    int repo_size;

    long time_millis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        hum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);





    }
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, hum, SensorManager.SENSOR_DELAY_NORMAL);





    }


    public final void onSensorChanged(SensorEvent event) {

        sensorValue = event.values[0];

        // Do something with this sensor data.
        String envInfo;
        currType=event.sensor.getType();

        time_millis = System.currentTimeMillis() + ((event.timestamp- SystemClock.elapsedRealtimeNanos())/1000000L);
        Timestamp date_time = new Timestamp(time_millis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date_time);

        repo_size = repo.temp_repo.size();
        RepoValues new_event = new RepoValues(time, sensorValue, time_millis);
        ext_detected = false;

        switch(currType){
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                envInfo=sensorValue+" Celsius";
                TextView txtView = findViewById(R.id.txtTEMP);
                txtView.setText(" "+envInfo);

                if (sensorValue > repo.max_temp.value){
                    repo.max_temp = new_event;
                    ext_detected = true;
                }
                if (sensorValue < repo.min_temp.value){
                    repo.min_temp = new_event;
                    ext_detected = true;
                }

                if (time_millis + 5000 <= repo.temp_repo.get(repo_size - 1).time_millis){

                    if (repo_size >= 10){
                        repo.temp_repo.remove(0);
                    }

                    repo.temp_repo.add(new_event);
                }



                break;

            case Sensor.TYPE_LIGHT:
                envInfo=sensorValue+" lm";
                TextView txtView2 = findViewById(R.id.txtLIGHT);
                txtView2.setText(" "+envInfo);

                if (sensorValue > repo.max_light.value){
                    repo.max_light = new_event;
                    ext_detected = true;
                }
                if (sensorValue < repo.min_light.value){
                    repo.min_light = new_event;
                    ext_detected = true;
                }

                if (time_millis + 5000 <= repo.light_repo.get(repo_size - 1).time_millis){

                    if (repo_size >= 10){
                        repo.light_repo.remove(0);
                    }

                    repo.light_repo.add(new_event);
                }


                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                envInfo=sensorValue+" percent humidity";
                TextView txtView3 = findViewById(R.id.txtHUM);
                txtView3.setText(" "+envInfo);


                if (sensorValue > repo.max_humid.value){
                    repo.max_humid = new_event;
                    ext_detected = true;
                }
                if (sensorValue < repo.min_humid.value){
                    repo.min_humid = new_event;
                    ext_detected = true;
                }

                if (time_millis + 5000 <= repo.humid_repo.get(repo_size - 1).time_millis){

                    if (repo_size >= 10){
                        repo.humid_repo.remove(0);
                    }

                    repo.humid_repo.add(new_event);
                }


                break;
            default: break;

        }




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String accuracyMsg = "";
        switch(accuracy){
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyMsg="Sensor has high accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyMsg="Sensor has medium accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyMsg="Sensor has low accuracy";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyMsg="Sensor has unreliable accuracy";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();
    }
    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
