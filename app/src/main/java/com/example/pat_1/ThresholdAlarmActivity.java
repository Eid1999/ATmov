package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ThresholdAlarmActivity extends AppCompatActivity {
    public Switch s1,s2,s3;  //Switch Buttons
    ToggleButton toggle;     //On-Off Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        //Connect the buttons to the variables and set the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_alarm);
        s1 = (Switch) findViewById(R.id.switch1);
        s2 = (Switch) findViewById(R.id.switch2);
        s3 = (Switch) findViewById(R.id.switch3);
        toggle= (ToggleButton) findViewById(R.id.toggleButton);

        //Set the switches and the energy saver button to the previous saved
        s1.setChecked(Sensors.Horn.TempSwitch);
        s2.setChecked(Sensors.Horn.HumSwitch);
        s3.setChecked(Sensors.Horn.LumSwitch);
        toggle.setChecked(Sensors.Horn.energy_saver);

        //set the contents of the thresholds text boxes to the previous saved
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

        //if the energy saver is On only start the sensor service now
        if(Sensors.Horn.energy_saver) {
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            startService(intent);
        }

    }

    public void keepThreshold(View view) {
        //set the textboxes to variables
        EditText minTemp = (EditText) findViewById(R.id.minTemp);
        EditText maxTemp = (EditText) findViewById(R.id.maxTemp);
        EditText minLum = (EditText) findViewById(R.id.minLum);
        EditText maxLum = (EditText) findViewById(R.id.maxLum);
        EditText minHum = (EditText) findViewById(R.id.minHum);
        EditText maxHum = (EditText) findViewById(R.id.maxHum);
        String accuracyMsg = "";

        //check the minTemp box
        if (!TextUtils.isEmpty(minTemp.getText().toString())) { //check if contains text
            if (Float.parseFloat(minTemp.getText().toString().trim()) < -273.15) {  //check if it has a valid value
                accuracyMsg="Welcome to Absolute Zero";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.minTemp = Float.parseFloat(minTemp.getText().toString().trim()); //keep new threshold
            }
        }

        //check the maxTemp box
        if (!TextUtils.isEmpty(maxTemp.getText().toString())) { //check if contains text
            if (Float.parseFloat(maxTemp.getText().toString().trim()) > 1000000000) { //check if it has a valid value
                accuracyMsg="Too hot";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.maxTemp = Float.parseFloat(maxTemp.getText().toString().trim()); //keep new threshold
            }
        }

        //check the minLum box
        if (!TextUtils.isEmpty(minLum.getText().toString())) { //check if contains text
            if (Float.parseFloat(minLum.getText().toString().trim()) < 0) { //check if it has a valid value
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.minLum = Float.parseFloat(minLum.getText().toString().trim()); //keep new threshold
            }
        }

        //check the maxLum box
        if (!TextUtils.isEmpty(maxLum.getText().toString())) { //check if contains text
            if (Float.parseFloat(maxLum.getText().toString().trim()) >100000000) { //check if it contains a valid value
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.maxLum = Float.parseFloat(maxLum.getText().toString().trim()); //keep new threshold
            }
        }

        //check the minHum box
        if (!TextUtils.isEmpty(minHum.getText().toString())) { //check if contains text
            if (Float.parseFloat(minHum.getText().toString().trim()) < 0) { //check if it contains a valid value
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.minHum = Float.parseFloat(minHum.getText().toString().trim()); //keep new threshold
            }
        }

        //check the maxHum box
        if (!TextUtils.isEmpty(maxHum.getText().toString())) { //check if contains text
            if (Float.parseFloat(maxHum.getText().toString().trim()) >1000000000) { //check if it contains a valid value
                accuracyMsg="Invalid Value";
                Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
                accuracyToast.show();
            } else {
                Sensors.Horn.maxHum = Float.parseFloat(maxHum.getText().toString().trim()); //keep new threshold
            }
        }

        //check all the switches and keep the their states/positions
        if(s1.isChecked()) {
            Sensors.Horn.TempSwitch = true;
        } else {
            Sensors.Horn.TempSwitch = false;
        }
        if(s2.isChecked()) {
            Sensors.Horn.HumSwitch = true;
        } else {
            Sensors.Horn.HumSwitch = false;
        }
        if(s3.isChecked()) {
            Sensors.Horn.LumSwitch = true;
        } else {
            Sensors.Horn.LumSwitch = false;
        }

        //check the energy saver button and keep its state/position
        if (toggle.isChecked()) {
            Sensors.Horn.energy_saver=true;
        } else {
            Sensors.Horn.energy_saver=false;
        }

    }

    //Click the reset repository button activates this method
    public void resetRepo (View view){
        Sensors.repo = new RepoStorage(); //reset the repository
    }

    //Go back, without clicking the top left arrow, activates this method
    public void goback(View view){
        if(Sensors.Horn.energy_saver) { //if energy saver mode is active close the sensors service
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            stopService(intent);
        }
        finish();
    }

    //Ckick the top left arrow activates this method
    public void onBackPressed(){
        if(Sensors.Horn.energy_saver) { //if energy saver mode is active close the sensors service
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            stopService(intent);
        }
        finish();
    }
}




