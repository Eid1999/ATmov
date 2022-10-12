package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    float minT = (float) -273.1, maxT = 100, minH = 0, maxH = 100, minL = 0, maxL = 40000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_alarm);

    }


    public void keepThreshold(View view) {

        EditText minTemp = (EditText) findViewById(R.id.minTemp);
        EditText maxTemp = (EditText) findViewById(R.id.maxTemp);
        EditText minHum = (EditText) findViewById(R.id.minHum);
        EditText maxHum = (EditText) findViewById(R.id.maxHum);
        EditText minLum = (EditText) findViewById(R.id.minLum);
        EditText maxLum = (EditText) findViewById(R.id.maxLum);
        Intent intent = new Intent(this, Alarm.class);


        Switch s1 = (Switch) findViewById(R.id.switch1);
        s1.setChecked(false);
        Switch s2 = (Switch) findViewById(R.id.switch2);
        s2.setChecked(false);
        Switch s3 = (Switch) findViewById(R.id.switch3);
        s3.setChecked(false);
        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            minT = Float.parseFloat(minTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxTemp.getText().toString())) {
            maxT = Float.parseFloat(maxTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minHum.getText().toString())) {
            minH = Float.parseFloat(minHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxHum.getText().toString())) {
            maxH = Float.parseFloat(maxHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minLum.getText().toString())) {
            minL = Float.parseFloat(minLum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(maxLum.getText().toString())) {
            maxL = Float.parseFloat(maxLum.getText().toString().trim());
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

    }
}




