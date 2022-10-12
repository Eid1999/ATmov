package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), Sensors.class );
        startService(intent);
        Intent intent1 = new Intent(this, Alarm.class);
        startService(intent1);




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




