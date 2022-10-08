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
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.concurrent.TimeUnit;


public class SensorActivity extends Activity{
    private SensorManager sensorManager;
    public Sensor envSense,temp,light, hum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);



    }
    String BUTTON_ACTION = "button";

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("Sensors"));
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            String envInfo;
            String className = intent.getStringExtra("Class");
            switch (className) {

                case "Temp":
                    envInfo = intent.getStringExtra("Stemp");
                    TextView txtView = findViewById(R.id.txtTEMP);
                    txtView.setText(" " + envInfo);

                    break;

                case "Light":
                    envInfo = intent.getStringExtra("Slight");
                    TextView txtView2 = findViewById(R.id.txtLIGHT);
                    txtView2.setText(" " + envInfo);


                    break;
                case "Humidity":
                    envInfo = intent.getStringExtra("Shum");
                    TextView txtView3 = findViewById(R.id.txtHUM);
                    txtView3.setText(" " + envInfo);


                    break;
                default:
                    break;

            }


        };
    };
    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


}
