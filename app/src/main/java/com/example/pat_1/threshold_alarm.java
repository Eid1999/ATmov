package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class threshold_alarm extends AppCompatActivity {
    public Switch s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_alarm);
        s1 = (Switch) findViewById(R.id.switch1);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {

            }
        });
        s2 = (Switch) findViewById(R.id.switch2);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {

            }
        });
        s3 = (Switch) findViewById(R.id.switch3);
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {

            }
        });
    }

    public void keepThreshold(View view) {
        EditText minTemp = (EditText) findViewById(R.id.minTemp);
        EditText maxTemp = (EditText) findViewById(R.id.maxTemp);
        EditText minLum = (EditText) findViewById(R.id.minLum);
        EditText maxLum = (EditText) findViewById(R.id.maxLum);
        EditText minHum = (EditText) findViewById(R.id.minHum);
        EditText maxHum = (EditText) findViewById(R.id.maxHum);


        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            Sensors.Horn.minTemp = Float.parseFloat(minTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxTemp.getText().toString())) {
            Sensors.Horn.maxTemp = Float.parseFloat(maxTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minLum.getText().toString())) {
            Sensors.Horn.minLum = Float.parseFloat(minHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxLum.getText().toString())) {
            Sensors.Horn.maxLum = Float.parseFloat(maxHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minHum.getText().toString())) {
            Sensors.Horn.minHum = Float.parseFloat(minLum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxHum.getText().toString())) {
            Sensors.Horn.maxHum = Float.parseFloat(maxLum.getText().toString().trim());
        }

        if(Sensors.Horn.TempSwitch!=s1.isChecked()) {
            Sensors.Horn.TempSwitch = s1.isChecked();
        }

        if(Sensors.Horn.LumSwitch!=s2.isChecked()) {
            Sensors.Horn.LumSwitch = s2.isChecked();
        }
        if(Sensors.Horn.LumSwitch!=s2.isChecked()) {
            Sensors.Horn.HumSwitch = s3.isChecked();
        }

    }

}




