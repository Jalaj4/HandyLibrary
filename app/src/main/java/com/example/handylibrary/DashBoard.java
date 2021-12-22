package com.example.handylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity {

    private TextView disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        disp = findViewById(R.id.disp);
        Intent intent = getIntent();
        String displayEmail = intent.getStringExtra("USERNAME");
        disp.setText("Welcome "+displayEmail);
    }
}