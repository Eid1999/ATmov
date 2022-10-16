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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "Pat_1Storage";
    public static Alarm Horn = new Alarm();
    public static RepoStorage repo = new RepoStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        // read settings and threshold file if it exists
        Object []data;
        data = (Object[]) readData();

        // checks if the file exists and stores the settings and the repository if so
        if (data != null){
            MainActivity.repo = (RepoStorage) data [0];
            MainActivity.Horn = (Alarm) data [1];
        }

        // if the app is in energy saver mode we don´t start the sensor service
        // if it is in normal mode, start the sensors service in the background
        if(!MainActivity.Horn.energy_saver) {
            Intent intent = new Intent(getApplicationContext(), Sensors.class);
            startService(intent);
        }

        // Start the exit service which will allow us to save data to a file when the app closes
        Intent intent = new Intent(this, ExitService.class);
        startService(intent);

        setContentView(R.layout.activity_main);

    }



    /** Called when the user taps the Show Sensors button */
    public void show_sensors(View view) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }



    /** Called when the user taps the Alarm Threshold button */
    public void change_threshold(View view) {
        Intent intent = new Intent(this, ThresholdAlarmActivity.class);
        startActivity(intent);
    }



    /** Called when the user taps the Show Repository button */
    public void show_repository(View view) {
        Intent intent = new Intent(this, RepositoryActivity.class);
        startActivity(intent);
    }


    /** Function called to read data from the settings and repository file */
    public Object readData() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            return new ObjectInputStream(fis).readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}




