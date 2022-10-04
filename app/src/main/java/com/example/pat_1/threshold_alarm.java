package com.example.pat_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class threshold_alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold_alarm);
        Editable new_threshold;

        /*EditText edit_txt = (EditText) findViewById(R.id.minTemp);

        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent.KEYCODE_ENTER == EditorInfo.IME_ACTION_DONE) {
                    new_threshold = new_num.getText();
                    return true;
                }
                return false;
            }
        });*/

        EditText new_num = findViewById(R.id.minTemp);
        new_threshold = new_num.getText();
    }




}