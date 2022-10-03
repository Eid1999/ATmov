package com.example.pat_1;


import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    public Sensor envSense,temp,light, hum;


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
        float sensorValue = event.values[0];


        // Do something with this sensor data.
        String envInfo;
        int currType=event.sensor.getType();

        switch(currType){
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                envInfo=sensorValue+" Celsius";
                TextView txtView = findViewById(R.id.txtTEMP);
                txtView.setText(" "+envInfo);
                //if (current time += 1 minute) <= repository[0][0]{
                //      temp_repo.remove(9);
                //      temp_repo.add(envInfo)
                //}
                break;

            case Sensor.TYPE_LIGHT:
                envInfo=sensorValue+" lm";
                TextView txtView2 = findViewById(R.id.txtLIGHT);
                txtView2.setText(" "+envInfo);

                //if (current time += 1 minute) <= repository[0][0]{
                //      light_repo.remove(9);
                //      light_repo.add(envInfo)
                //}

                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                envInfo=sensorValue+" percent humidity";
                TextView txtView3 = findViewById(R.id.txtHUM);
                txtView3.setText(" "+envInfo);

                //if (current time += 1 minute) <= repository[0][0]{
                //      humid_repo.remove(9);
                //      humid_repo.add(envInfo)
                //}

                break;
            default: break;
        }

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
