package com.example.kursovaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.kursovaya.Model.Users;
import com.example.kursovaya.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class activity_enter extends AppCompatActivity {

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if(UserPhoneKey != "" && UserPasswordKey != "")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey))
            {
                ValidateUser(UserPhoneKey, UserPasswordKey);

                loadingBar.setTitle("Вход в приложение");
                loadingBar.setMessage("Пожалуйста, подождите...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }

    private void ValidateUser( String phone, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phone).exists())
                {
                    Users usersDate = snapshot.child("Users").child(phone).getValue(Users.class);

                    if(usersDate.getPhone().equals(phone))
                    {
                        if(usersDate.getPassword().equals(password))
                        {
                            loadingBar.dismiss();
                            Toast.makeText(activity_enter.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(activity_enter.this, activity_hot_tours.class);
                            startActivity(intent);
                        }
                        else
                        {
                            loadingBar.dismiss();

                        }
                    }
                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(activity_enter.this, "Аккаунт с номеров " + phone + " не существует", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity_enter.this, activity_registr.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void enter1(View v) {
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }

    public void registration(View v) {
        Intent intent = new Intent(this, activity_registr.class);
        startActivity(intent);
    }
}