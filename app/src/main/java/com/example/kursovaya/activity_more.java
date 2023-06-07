package com.example.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;

public class activity_more extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more1);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public void Find_tours(View v) {
        Intent intent = new Intent(this, activity_main.class);
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
    public void About(View v) {
        Intent intent = new Intent(this, activity_about_us.class);
        startActivity(intent);
    }
    public void Contacts(View v) {
        Intent intent = new Intent(this, activity_contacts.class);
        startActivity(intent);
    }
    public void Reviews(View v) {
        Intent intent = new Intent(this, activity_reviews.class);
        startActivity(intent);
    }

    public void Sales(View v) {
        Intent intent = new Intent(this, activity_sales.class);
        startActivity(intent);
    }

    public void Exit(View v) {
        Paper.book().destroy();
        Intent intent = new Intent(this, activity_enter.class);
        startActivity(intent);
    }
}