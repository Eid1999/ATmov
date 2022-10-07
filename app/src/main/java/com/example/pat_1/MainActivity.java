package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    public Sensor envSense,temp,light, hum;
    public String Slight,Shum,Stemp;


    //public ArrayList<Float> temp_repo = new ArrayList<Float>();
    //public ArrayList<Float> light_repo = new ArrayList<Float>();
    //public ArrayList<Float> humid_repo = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        hum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

    }
    /** Called when the user taps the Show Sensors button */
    public void show_sensors(View view) {
        Intent intent = new Intent(this, SensorActivity.class);

        startActivity(intent);
    }

    /** Called when the user taps the Alarm Threshold button */
    public void change_threshold(View view) {
        Intent intent = new Intent(this, threshold_alarm.class);
        startActivity(intent);
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
                Stemp=sensorValue+" Celsius";
                //if (current time += 1 minute) <= repository[0][0]{
                //      temp_repo.remove(9);
                //      temp_repo.add(envInfo)
                //}
                break;

            case Sensor.TYPE_LIGHT:
                Slight=sensorValue+" lm";

                //if (current time += 1 minute) <= repository[0][0]{
                //      light_repo.remove(9);
                //      light_repo.add(envInfo)
                //}

                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                Shum=sensorValue+" percent humidity";

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

