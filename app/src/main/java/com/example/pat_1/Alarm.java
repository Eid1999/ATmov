package com.example.pat_1;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Alarm extends Service {
    protected float[] limits;
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        Alarm getService() {
            // Return this instance of LocalService so clients can call public methods
            return Alarm.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        limits = extras.getFloatArray("thresholds");

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            String envInfo;
            String className = intent.getStringExtra("Class");
            switch (className) {

                case "Temp":
                    envInfo = intent.getStringExtra("Stemp");
                    if (Float.valueOf(envInfo) < limits [0]) {
                        onAlarm("minT");
                    } else if (Float.valueOf(envInfo) > limits [1]){
                        onAlarm("maxT");
                    }
                    break;

                case "Light":
                    envInfo = intent.getStringExtra("Slight");
                    if (Float.valueOf(envInfo) < limits [2] || Float.valueOf(envInfo) > limits [3]) {
                        onAlarm();
                    }

                    break;
                case "Humidity":
                    envInfo = intent.getStringExtra("Shum");
                    if (Float.valueOf(envInfo) < limits[4] || Float.valueOf(envInfo) > limits[5]) {
                        onAlarm();
                    }

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

    public void onAlarm (String horn) {
        String accuracyMsg = "";
        switch(horn){
            case "minT":
                accuracyMsg="Minimum temperature threshold reached!";
                break;
            case "maxT":
                accuracyMsg="Maximum temperature threshold reached!";
                break;
            case "minH":
                accuracyMsg="Minimum humidity threshold reached!";
                break;
            case "maxH":
                accuracyMsg="Maximum humidity threshold reached!";
                break;
            case "minL":
                accuracyMsg="Minimum luminosity threshold reached!";
                break;
            case "maxL":
                accuracyMsg="Maximum luminosity threshold reached!";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();
    }
}