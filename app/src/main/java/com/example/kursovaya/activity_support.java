package com.example.kursovaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class activity_support extends AppCompatActivity {

    private Button registerBtn;
    private EditText usernameInput, phoneInput, questionInput;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        registerBtn = (Button) findViewById(R.id.Best_tours);
        usernameInput = (EditText) findViewById(R.id.edit_name);
        phoneInput = (EditText) findViewById(R.id.edit_phone);
        questionInput = (EditText) findViewById(R.id.edit_question);
        loadingBar = new ProgressDialog(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String username = usernameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String question = questionInput.getText().toString();

        if(TextUtils.isEmpty(question))
        {
            Toast.makeText(this, "Введите вопрос", Toast.LENGTH_SHORT). show();
        }
        else if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT). show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Введите контактный телефон", Toast.LENGTH_SHORT). show();
        }
        else
        {
            loadingBar.setTitle("Запрос отправляется");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhone(username, phone, question);
        }
    }

    private void ValidatePhone(String username, String phone, String question) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("phone", phone);
                    userDataMap.put("name", username);
                    userDataMap.put("question", question);

                    RootRef.child("Questions").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(activity_support.this, "Ожидайте звонка!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(activity_support.this, activity_support.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(activity_support.this, "Ошибка.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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