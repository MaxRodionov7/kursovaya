package com.example.kursovaya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class activity_add_new_country extends AppCompatActivity {

private String countryName, Description, Price, Hname, saveCurrentDate, saveCurrentTime, HotelRandomKey;
private String downloadImageUrl;
private ImageView hotelImage;
private EditText hotelName, hotelDescription, hotelPrice;
private Button addNewHotelButton;

private Uri ImageUri;

private StorageReference HotelImageRef;

private DatabaseReference HotelsRef;

private ProgressDialog loadingBar;

private static final int GALLERYPICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_country);

        initi();
        countryName = getIntent().getExtras().get("country").toString();

        hotelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        addNewHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateHotelData();

            }
        });
    }

    private void ValidateHotelData() {
        Description = hotelDescription.getText().toString();
        Price = hotelPrice.getText().toString();
        Hname = hotelName.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this, "Добавьте изображение", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Добавьте описание", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Добавьте стоимость", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Hname))
        {
            Toast.makeText(this, "Добавьте название", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreHotelInf();
        }
    }

    private void StoreHotelInf() {

        loadingBar.setTitle("Загрузка данных");
        loadingBar.setMessage("Пожалуйста, подождите...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentTime = currentTime.format(calendar.getTime());

            HotelRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = HotelImageRef.child(ImageUri.getLastPathSegment() + HotelRandomKey + ".jpeg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(activity_add_new_country.this, "Ошибка: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(activity_add_new_country.this, "Изображение успешно загружено!", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(activity_add_new_country.this, "Фото сохранено", Toast.LENGTH_SHORT).show();
                            SaveHotelInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveHotelInfoToDatabase() {
        HashMap<String, Object> hotelMap = new HashMap<>();

        hotelMap.put("hid", HotelRandomKey);
        hotelMap.put("date", saveCurrentDate);
        hotelMap.put("time", saveCurrentTime);
        hotelMap.put("description", Description);
        hotelMap.put("image", downloadImageUrl);
        hotelMap.put("country", countryName);
        hotelMap.put("price", Price);
        hotelMap.put("hname", Hname);

        HotelsRef.child(HotelRandomKey).updateChildren(hotelMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    loadingBar.dismiss();
                    Toast.makeText(activity_add_new_country.this, "Отель добавлен", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity_add_new_country.this, activity_admin_country.class);
                    startActivity(intent);
                }

                else
                {
                    String message = task.getException().toString();;
                    Toast.makeText(activity_add_new_country.this, "Ошибка: " + message, Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERYPICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERYPICK && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            hotelImage.setImageURI(ImageUri);
        }
    }

    private void initi() {
        countryName = getIntent().getExtras().get("country").toString();
        hotelImage = findViewById(R.id.select_hotel_image);
        hotelName = findViewById(R.id.hotel_name);
        hotelDescription = findViewById(R.id.description);
        hotelPrice = findViewById(R.id.price);
        addNewHotelButton = findViewById(R.id.btn_add_new_hotel);
        HotelImageRef = FirebaseStorage.getInstance().getReference().child("Hotel Images");
        HotelsRef = FirebaseDatabase.getInstance().getReference().child("Hotels");
        loadingBar = new ProgressDialog(this);
    }
}