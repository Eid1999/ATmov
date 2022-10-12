package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class threshold_alarm extends AppCompatActivity {
    Alarm mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_alarm);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    public void keepThreshold(View view) {
        Intent intent = new Intent(this, Alarm.class);
        float [] thresholds = {-273.1f, 100f, 0f, 100f, 0f, 40000f};
        EditText minTemp = (EditText) findViewById(R.id.minTemp);
        EditText maxTemp = (EditText) findViewById(R.id.maxTemp);
        EditText minHum = (EditText) findViewById(R.id.minHum);
        EditText maxHum = (EditText) findViewById(R.id.maxHum);
        EditText minLum = (EditText) findViewById(R.id.minLum);
        EditText maxLum = (EditText) findViewById(R.id.maxLum);


        Switch s1 = (Switch) findViewById(R.id.switch1);
        s1.setChecked(false);
        Switch s2 = (Switch) findViewById(R.id.switch2);
        s2.setChecked(false);
        Switch s3 = (Switch) findViewById(R.id.switch3);
        s3.setChecked(false);
        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            thresholds[0] = Float.parseFloat(minTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxTemp.getText().toString())) {
            thresholds[1] = Float.parseFloat(maxTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minHum.getText().toString())) {
            thresholds[2] = Float.parseFloat(minHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxHum.getText().toString())) {
            thresholds[3] = Float.parseFloat(maxHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minLum.getText().toString())) {
            thresholds[4] = Float.parseFloat(minLum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxLum.getText().toString())) {
            thresholds[5] = Float.parseFloat(maxLum.getText().toString().trim());
        }

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                intent.putExtra("s1", isChecked);
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                intent.putExtra("s2", isChecked);
            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                intent.putExtra("s3", isChecked);
            }
        });
        intent.putExtra("thresholds", thresholds);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            Alarm.LocalBinder binder = (Alarm.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}




