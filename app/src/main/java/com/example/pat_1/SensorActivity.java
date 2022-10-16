package com.example.pat_1;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.concurrent.TimeUnit;


public class SensorActivity extends AppCompatActivity {
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

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        if(Sensors.Horn.energy_saver) {//Start Sensors Sevices(ENERGY MODE ON)
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            startService(intent);
        }

    }
    String BUTTON_ACTION = "button";

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("Sensors"));//Create Receiver of Service

    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) { //Reiceves informantion from the broadcast and distinguish with type it is (from with sensor)

            float envInfo;
            String className = intent.getStringExtra("Class");
            switch (className) {

                case "Temp":
                    //Writes information of the Temperature Sensor on the interface
                    envInfo = intent.getFloatExtra("Stemp",-1);
                    TextView txtView = findViewById(R.id.txtTEMP);
                    txtView.setText(" " + envInfo +" ºC");
                    break;

                case "Light":
                    //Writes information of the Light Sensor on the interface
                    envInfo = intent.getFloatExtra("Slight",-1);
                    TextView txtView2 = findViewById(R.id.txtLIGHT);
                    txtView2.setText(" " + envInfo +" lm");
                    break;
                case "Humidity":
                    //Writes information of the Humidity Sensor on the interface
                    envInfo = intent.getFloatExtra("Shum",-1);
                    TextView txtView3 = findViewById(R.id.txtHUM);
                    txtView3.setText(" " + envInfo+" %");
                    break;
                default:
                    break;

            }


        };
    };

    @Override
    protected void onDestroy() { //Removes Broadcast
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    //Go back, without clicking the top left arrow, activates this method
    public void goback(View view){
        if(Sensors.Horn.energy_saver) { //if energy saver mode is active close the sensors service
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            stopService(intent);
        }
        finish();
    }

    //Click the top left arrow activates this method
    public void onBackPressed(){
        if(Sensors.Horn.energy_saver) { //if energy saver mode is active close the sensors service
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            stopService(intent);
        }
        finish();
    }

}
