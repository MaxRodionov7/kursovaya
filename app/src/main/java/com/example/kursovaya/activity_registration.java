package com.example.kursovaya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovaya.Model.Hotels;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class activity_registration extends AppCompatActivity {

    DatabaseReference HotelsRef;

    private Button registerBtn;
    private EditText usernameInput, phoneInput, userPasport, validatePasport;
    private ProgressDialog loadingBar;



    TextView hotel_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        HotelsRef = FirebaseDatabase.getInstance().getReference().child("Hotels");

        hotel_name = (TextView) findViewById(R.id.hotel_name);

        Intent intent = getIntent();

        String hname = intent.getStringExtra("hname");


        hotel_name.setText(hname);

        registerBtn = (Button) findViewById(R.id.Next1);
        usernameInput = (EditText) findViewById(R.id.editTextDate5);
        userPasport = (EditText) findViewById(R.id.editNights);
        validatePasport = (EditText) findViewById(R.id.edit1);
        phoneInput = (EditText) findViewById(R.id.editPeoples);
        loadingBar = new ProgressDialog(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String HotelName = hotel_name.getText().toString();
        String username = usernameInput.getText().toString();
        String pasport = userPasport.getText().toString();
        String validate = validatePasport.getText().toString();
        String phone = phoneInput.getText().toString();


        if(TextUtils.isEmpty(pasport))
        {
            Toast.makeText(this, "Введите серию и номер паспорта", Toast.LENGTH_SHORT). show();
        }
        else if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT). show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Введите контактный телефон", Toast.LENGTH_SHORT). show();
        }
        else if (TextUtils.isEmpty(validate))
        {
            Toast.makeText(this, "Введите срок действия паспорта", Toast.LENGTH_SHORT). show();
        }
        else
        {
            loadingBar.setTitle("Запрос отправляется");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhone(username, phone, pasport, validate, HotelName);
        }
    }

    private void ValidatePhone(String username, String phone, String pasport, String validate, String HotelName) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                HashMap<String, Object> userDataMap = new HashMap<>();
                userDataMap.put("phone", phone);
                userDataMap.put("name", username);
                userDataMap.put("pasport", pasport);
                userDataMap.put("validate_period", validate);
                userDataMap.put("hotel_name", HotelName);

                RootRef.child("Reservations").child(phone).updateChildren(userDataMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    loadingBar.dismiss();
                                    Toast.makeText(activity_registration.this, "Бронирование создано!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(activity_registration.this, activity_hot_tours.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    loadingBar.dismiss();
                                    Toast.makeText(activity_registration.this, "Ошибка.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void Back (View v){
        Intent intent = new Intent(this, activity_hot_tours.class);
        startActivity(intent);
    }

}