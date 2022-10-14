package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_main);
        if(Sensors.Horn==null||Sensors.Horn.energy_saver==false) {
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            startService(intent);
        }
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

    /** Called when the user taps the Show Repository button */
    public void show_repository(View view) {
        Intent intent = new Intent(this, RepositoryActivity.class);
        startActivity(intent);
    }





}




