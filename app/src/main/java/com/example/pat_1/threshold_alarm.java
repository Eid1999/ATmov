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
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class threshold_alarm extends AppCompatActivity {
    public Switch s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

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






        TextView txtView = findViewById(R.id.maxTemp);
        txtView.setText(" " + Sensors.Horn.maxTemp);


        TextView txtView1 = findViewById(R.id.minTemp);
        txtView1.setText(" " + Sensors.Horn.minTemp);

        TextView txtView2 = findViewById(R.id.maxHum);
        txtView2.setText(" " + Sensors.Horn.maxHum );

        TextView txtView3 = findViewById(R.id.minHum);
        txtView3.setText(" " + Sensors.Horn.minHum);

        TextView txtView4 = findViewById(R.id.minLum);
        txtView4.setText(" " + Sensors.Horn.minLum);

        TextView txtView5 = findViewById(R.id.maxLum);
        txtView5.setText(" " + Sensors.Horn.maxLum);






    }


    public void keepThreshold(View view) {
        EditText minTemp = (EditText) findViewById(R.id.minTemp);
        EditText maxTemp = (EditText) findViewById(R.id.maxTemp);
        EditText minLum = (EditText) findViewById(R.id.minLum);
        EditText maxLum = (EditText) findViewById(R.id.maxLum);
        EditText minHum = (EditText) findViewById(R.id.minHum);
        EditText maxHum = (EditText) findViewById(R.id.maxHum);
        String accuracyMsg = "";


        if (!TextUtils.isEmpty(minTemp.getText().toString())) {
            if (Float.parseFloat(minTemp.getText().toString().trim()) < -273.15) {
                accuracyMsg="Welcome to Absolute Zero";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.minTemp = Float.parseFloat(minTemp.getText().toString().trim());
            }
        }

        if (!TextUtils.isEmpty(maxTemp.getText().toString())) {
            if (Float.parseFloat(maxTemp.getText().toString().trim()) > 1000000000) {
                accuracyMsg="Too hot";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.maxTemp = Float.parseFloat(maxTemp.getText().toString().trim());
            }
        }

        if (!TextUtils.isEmpty(minLum.getText().toString())) {
            if (Float.parseFloat(minLum.getText().toString().trim()) < 0) {
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.minLum = Float.parseFloat(minHum.getText().toString().trim());
            }
        }

        if (!TextUtils.isEmpty(maxLum.getText().toString())) {
            if (Float.parseFloat(maxLum.getText().toString().trim()) >100000000) {
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.maxLum = Float.parseFloat(maxHum.getText().toString().trim());
            }
        }

        if (!TextUtils.isEmpty(minHum.getText().toString())) {
            if (Float.parseFloat(minHum.getText().toString().trim()) < 0) {
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.minHum = Float.parseFloat(minLum.getText().toString().trim());
            }
        }

        if (!TextUtils.isEmpty(maxHum.getText().toString())) {
            if (Float.parseFloat(maxHum.getText().toString().trim()) >1000000000) {
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.maxHum = Float.parseFloat(maxLum.getText().toString().trim());
            }
        }

        if(Sensors.Horn.TempSwitch!=s1.isChecked()) {
            Sensors.Horn.TempSwitch = s1.isChecked();
        }

        if(Sensors.Horn.LumSwitch!=s3.isChecked()) {
            Sensors.Horn.LumSwitch = s3.isChecked();
        }
        if(Sensors.Horn.LumSwitch!=s2.isChecked()) {
            Sensors.Horn.HumSwitch = s2.isChecked();
        }

    }

    public void resetRepo (View view){
        Sensors.repo = new RepoStorage();
    }

    public void goback(View view){
        finish();
    }
}




