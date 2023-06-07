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

public class activity_flight2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight2);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        final List<String> states = Arrays.asList("Одно багажное место", "Два багажных места", "Без багажа");
        final Spinner spinner1 = findViewById(R.id.spinner3);

        ArrayAdapter adapter1 = new ArrayAdapter(getApplicationContext(), R.layout.my_selected_item, states);
        adapter1.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner1.setAdapter(adapter1);
    }
    public void Back(View v) {
        Intent intent = new Intent(this, activity_flight1.class);
        startActivity(intent);
    }
    public void Next1(View v) {
        Intent intent = new Intent(this, activity_flight3.class);
        startActivity(intent);
    }
}