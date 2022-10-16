/*
*  ATMov Sensor Application Project *
*
* Authors:
* André Ferreira 96158
* João Santos 96242
* Pedro Eid 91592
*
* */

package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.view.Window;
import android.view.WindowManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        Object []data;
        data = (Object[]) readData(); //Read file with saved data from last session

        if (data != null){ //Iniciate Global Variables with data from file
            Sensors.repo = (RepoStorage) data [0];
            Sensors.Horn = (Alarm) data [1];
        }
        setContentView(R.layout.activity_main);
        if(Sensors.Horn==null||!Sensors.Horn.energy_saver) {//Activite the Sensor Service if energy save mode turn off
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            startService(intent);
        }



    }
    /** Called when the user taps the Show Sensors button */
    public void show_sensors(View view) {//Sensor Acitvity
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Alarm Threshold button */
    public void change_threshold(View view) {//Threshold(Settings) Activity
        Intent intent = new Intent(this, ThresholdAlarmActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Show Repository button */
    public void show_repository(View view) {//Repository Acitivity
        Intent intent = new Intent(this, RepositoryActivity.class);
        startActivity(intent);
    }


    public Object readData() {//Read File
        try {
            FileInputStream fis = openFileInput(Sensors.FILE_NAME);
            return new ObjectInputStream(fis).readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}




