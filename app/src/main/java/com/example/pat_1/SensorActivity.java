package com.example.pat_1;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;


public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    public Sensor envSense,temp,light, hum;
    private TextView ambientValue, lightValue, humidityValue;
    private final int AMBIENT=0;
    private TextView[] valueFields = new TextView[4];

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
        sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, hum, SensorManager.SENSOR_DELAY_NORMAL);



        /*light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light==null)
            Toast.makeText(this.getApplicationContext(),
                    "Sorry - your device doesn't have an ambient light sensor!",
                    Toast.LENGTH_SHORT).show();
        else
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);


        hum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(hum==null)
            Toast.makeText(this.getApplicationContext(),
                    "Sorry - your device doesn't have an ambient temperature sensor!",
                    Toast.LENGTH_SHORT).show();
        else
            sensorManager.registerListener(this, hum, SensorManager.SENSOR_DELAY_NORMAL);*/

    }
    public final void onSensorChanged(SensorEvent event) {
        float sensorValue = event.values[0];


        // Do something with this sensor data.
        TextView currValue = ambientValue;
        String envInfo="";
        int currType=event.sensor.getType();



        switch(currType){
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                envInfo=sensorValue+" degrees Celsius";
                setContentView(R.layout.activity_sensor);
                TextView txtView = (TextView) findViewById(R.id.txtTEMP);
                txtView.setText(" "+envInfo);
                break;
            /*case Sensor.TYPE_LIGHT:
                envInfo=sensorValue+" SI lux units";
                setContentView(R.layout.activity_sensor);
                TextView txtView2 = (TextView) findViewById(R.id.txtLIGHT);
                txtView2.setText(" "+envInfo);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                envInfo=sensorValue+" percent humidity";
                setContentView(R.layout.activity_sensor);
                TextView txtView3 = (TextView) findViewById(R.id.txtHUM);
                txtView3.setText(" "+envInfo);
                break;
            default: break;*/
        }

        envSense=null;
        sensorManager.unregisterListener(this);
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
