package com.example.pat_1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Sensors extends Service implements SensorEventListener {
    private static final String DEBUG_TAG = "Sensors";
    private SensorManager sensorManager;
    public Sensor envSense,temp,light, hum;
    public String Slight,Shum,Stemp;

    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        hum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);


        sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, hum, SensorManager.SENSOR_DELAY_NORMAL);

        return flags;
    }



    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }



    public final void onSensorChanged(SensorEvent event) {
        float sensorValue = event.values[0];
        Intent intent = new Intent("Sensors");



        // Do something with this sensor data.
        String envInfo;
        int currType=event.sensor.getType();

        switch(currType){
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                Stemp=sensorValue+" Celsius";
                intent.putExtra("Stemp", Stemp);
                intent.putExtra("Class","Temp");




                //if (current time += 1 minute) <= repository[0][0]{
                //      temp_repo.remove(9);
                //      temp_repo.add(envInfo)
                //}
                break;

            case Sensor.TYPE_LIGHT:
                Slight=sensorValue+" lm";
                intent.putExtra("Slight", Slight);
                intent.putExtra("Class","Light");

                //if (current time += 1 minute) <= repository[0][0]{
                //      light_repo.remove(9);
                //      light_repo.add(envInfo)
                //}

                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                Shum=sensorValue+" percent humidity";
                intent.putExtra("Shum", Shum);
                intent.putExtra("Class","Humidity");


                break;
            default: break;


        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);





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


}




