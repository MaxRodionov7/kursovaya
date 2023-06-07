package com.example.kursovaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import io.paperdb.Paper;

public class activity_admin_country extends AppCompatActivity {

    private ImageView UK, Canada, USA;
    private ImageView China, Italy, Spain;
    private ImageView France, Germany, Turkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_country);
        
        initi();

        UK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "UK");
                startActivity(intent);
            }
        });

        Canada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "Canada");
                startActivity(intent);
            }
        });

        USA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "USA");
                startActivity(intent);
            }
        });

        China.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "China");
                startActivity(intent);
            }
        });

        Italy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "Italy");
                startActivity(intent);
            }
        });

        Spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "Spain");
                startActivity(intent);
            }
        });

        France.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "France");
                startActivity(intent);
            }
        });

        Germany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "Germany");
                startActivity(intent);
            }
        });

        Turkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_admin_country.this, activity_add_new_country.class);
                intent.putExtra("country", "Turkey");
                startActivity(intent);
            }
        });
    }

    public void Exit(View v) {
        Paper.book().destroy();
        Intent intent = new Intent(this, activity_enter.class);
        startActivity(intent);
    }

    private void initi() {
        UK = findViewById(R.id.uk);
        Canada = findViewById(R.id.canada);
        USA = findViewById(R.id.us);

        China = findViewById(R.id.china);
        Italy = findViewById(R.id.italy);
        Spain = findViewById(R.id.spain);

        France = findViewById(R.id.france);
        Germany = findViewById(R.id.germany);
        Turkey = findViewById(R.id.turkey);
    }
}