package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class RepositoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        Spinner drop_menu = (Spinner) findViewById(R.id.dropdown_menu);
        drop_menu.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

        String value = parent.getItemAtPosition(pos).toString();
        int repo_size[] = {Sensors.repo.temp_repo.size(), Sensors.repo.light_repo.size(), Sensors.repo.humid_repo.size()};
        TextView txtView;



        switch(value){
            case "Temperature":
                if (repo_size[0] > 0) {
                    txtView = findViewById(R.id.hour9);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 1).time);
                    txtView = findViewById(R.id.value9);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 1).value + "ºC");
                }if (repo_size[0] > 1) {
                    txtView = findViewById(R.id.hour8);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 2).time);
                    txtView = findViewById(R.id.value8);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 2).value + "ºC");
                }if (repo_size[0] > 2) {
                    txtView = findViewById(R.id.hour7);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 3).time);
                    txtView = findViewById(R.id.value7);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 3).value + "ºC");
                }if (repo_size[0] > 3) {
                    txtView = findViewById(R.id.hour6);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 4).time);
                    txtView = findViewById(R.id.value6);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 4).value + "ºC");
                }if (repo_size[0] > 4) {
                    txtView = findViewById(R.id.hour5);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 5).time);
                    txtView = findViewById(R.id.value5);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 5).value + "ºC");
                }if (repo_size[0] > 5) {
                    txtView = findViewById(R.id.hour4);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 6).time);
                    txtView = findViewById(R.id.value4);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 6).value + "ºC");
                }if (repo_size[0] > 6) {
                    txtView = findViewById(R.id.hour3);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 7).time);
                    txtView = findViewById(R.id.value3);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 7).value + "ºC");
                }if (repo_size[0] > 7) {
                    txtView = findViewById(R.id.hour2);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 8).time);
                    txtView = findViewById(R.id.value2);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 8).value + "ºC");
                }if (repo_size[0] > 8) {
                    txtView = findViewById(R.id.hour1);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 9).time);
                    txtView = findViewById(R.id.value1);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 9).value + "ºC");
                }if (repo_size[0] > 9) {
                    txtView = findViewById(R.id.hour0);
                    txtView.setText(Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 10).time);
                    txtView = findViewById(R.id.value0);
                    txtView.setText("" + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 10).value + "ºC");
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void refresh(View view){
        finish();
    }

    public void goback(View view){
        finish();
    }

}


    // create button to reset repository, maximums and minimums

