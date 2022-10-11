package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class threshold_alarm extends AppCompatActivity {
    float minT = (float) -273.1, maxT = 100, minH = 0, maxH = 100, minL = 0, maxL = 40000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_alarm);
    }


    public void keepThreshold(View view){

        EditText minTemp = (EditText) findViewById(R.id.minTemp);
        EditText maxTemp = (EditText) findViewById(R.id.maxTemp);
        EditText minHum = (EditText) findViewById(R.id.minHum);
        EditText maxHum = (EditText) findViewById(R.id.maxHum);
        EditText minLum = (EditText) findViewById(R.id.minLum);
        EditText maxLum = (EditText) findViewById(R.id.maxLum);

        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            minT = Float.parseFloat(minTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            maxT = Float.parseFloat(maxTemp.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            minH = Float.parseFloat(minHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            maxH = Float.parseFloat(maxHum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            minL = Float.parseFloat(minLum.getText().toString().trim());
        }

        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            maxL = Float.parseFloat(maxLum.getText().toString().trim());
        }
    }




}