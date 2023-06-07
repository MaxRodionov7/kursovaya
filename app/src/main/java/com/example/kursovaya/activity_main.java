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

public class activity_main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        final List<String> states = Arrays.asList("Все включено", "Завтрак, обед, ужин", "Завтрак, ужин", "Завтрак", "Без питания");
        final Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.my_selected_item, states);
        adapter.setDropDownViewResource(R.layout.my_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void Find_tours(View v) {
        Intent intent = new Intent(this, activity_main.class);
        startActivity(intent);
    }

    public void Find_tours1(View v) {
        Intent intent = new Intent(this, activity_hotels.class);
        startActivity(intent);
    }

    public void Hot_tours(View v) {
        Intent intent = new Intent(this, activity_hot_tours.class);
        startActivity(intent);
    }

    public void More(View v) {
        Intent intent = new Intent(this, activity_more.class);
        startActivity(intent);
    }
    public void Support(View v) {
        Intent intent = new Intent(this, activity_support.class);
        startActivity(intent);
    }
}
