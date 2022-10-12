package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class RepositoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        Spinner drop_menu = (Spinner) findViewById(R.id.dropdown_menu);
        drop_menu.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        String value = parent.getItemAtPosition(pos).toString();
        switch(value){
            case "Temperature":
                TextView txtView = findViewById(R.id.hour1);
                txtView.setText(" " + Sensors.repo.temp_repo.get(Sensors.repo.temp_repo.size() - 1).time);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


    // create button to reset repository, maximums and minimums

