package com.example.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class activity_flight1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight1);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        final List<String> states = Arrays.asList("9:00", "14:55", "18:47", "22:00");
        final Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), R.layout.my_selected_item, states);
        adapter2.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner2.setAdapter(adapter2);
    }
    public void Back(View v) {
        Intent intent = new Intent(this, activity_hotels.class);
        startActivity(intent);
    }
    public void Next1(View v) {
        Intent intent = new Intent(this, activity_flight2.class);
        startActivity(intent);
    }
}