package com.example.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class activity_hotels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public void Back(View v) {
        Intent intent = new Intent(this, activity_main.class);
        startActivity(intent);
    }
    public void Next(View v) {
        Intent intent = new Intent(this, activity_flight.class);
        startActivity(intent);
    }
}