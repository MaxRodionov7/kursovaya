package com.example.kursovaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursovaya.Model.Users;
import com.example.kursovaya.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class activity_login extends AppCompatActivity {

    private Button loginBtn;
    private EditText phoneInput, passwordInput;
    private ProgressDialog loadingBar;

    private TextView AdminLink, NotAdminLink;

    private String parentDbName = "Users";

    private CheckBox checkBoxRememberedMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.login_btn);
        phoneInput = (EditText) findViewById(R.id.number_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        loadingBar = new ProgressDialog(this);
        checkBoxRememberedMe = (CheckBox) findViewById(R.id.login_checkbox);
        Paper.init(this);

        AdminLink = (TextView)findViewById(R.id.admin_link);
        NotAdminLink = (TextView)findViewById(R.id.customer_link);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                loginBtn.setText("Вход для админа");
                parentDbName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                loginBtn.setText("Войти");
                parentDbName = "Users";
            }
        });
    }

    private void loginUser() {
        String phone = phoneInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT). show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT). show();
        }
        else
        {
            loadingBar.setTitle("Вход в приложение");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidateUser(phone, password);
        }
    }

    private void ValidateUser(String phone, String password) {

        if (checkBoxRememberedMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersDate = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if(usersDate.getPhone().equals(phone))
                    {
                        if(usersDate.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Users"))
                            {
                                loadingBar.dismiss();
                                Toast.makeText(activity_login.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(activity_login.this, activity_hot_tours.class);
                                startActivity(intent);
                            }
                            else if(parentDbName.equals("Admins"))
                            {
                                loadingBar.dismiss();
                                Toast.makeText(activity_login.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(activity_login.this, activity_admin_country.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(activity_login.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(activity_login.this, "Аккаунт с номеров " + phone + " не существует", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity_login.this, activity_registr.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}