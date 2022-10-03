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

public class MainActivity extends AppCompatActivity {
    private SensorManager senseManage;
    private Sensor envSense;

    //public ArrayList<Float> temp_repo = new ArrayList<Float>();
    //public ArrayList<Float> light_repo = new ArrayList<Float>();
    //public ArrayList<Float> humid_repo = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        senseManage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
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



}

