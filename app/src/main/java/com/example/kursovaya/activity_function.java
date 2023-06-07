package com.example.kursovaya;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_function extends AppCompatActivity {

    private Button bFlash;
    private activity_flash flashClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        init();
    }
    private void init() {
        bFlash = findViewById(R.id.b1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flashClass = new activity_flash(this);
        }
    }
    public void onClickFlash(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (flashClass.isFlash_status()){
                flashClass.flashOff();
                bFlash.setText("On");
            }
            else{
                flashClass.flashOn();
                bFlash.setText("Off");
            }
        }
    }
    public void Back(View v) {
        Intent intent = new Intent(this, activity_more.class);
        startActivity(intent);
    }
}