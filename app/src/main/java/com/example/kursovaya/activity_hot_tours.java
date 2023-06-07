package com.example.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursovaya.Model.Hotels;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class activity_hot_tours extends AppCompatActivity {

    DatabaseReference HotelsRef;

    TextView hname;
    private RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_tours);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        HotelsRef = FirebaseDatabase.getInstance().getReference().child("Hotels");

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void Find_tours (View v){
        Intent intent = new Intent(this, activity_main.class);
        startActivity(intent);
    }

    public void Hot_tours (View v){
        Intent intent = new Intent(this, activity_hot_tours.class);
        startActivity(intent);
    }

    public void More (View v){
        Intent intent = new Intent(this, activity_more.class);
        startActivity(intent);
    }
    public void Support (View v){
        Intent intent = new Intent(this, activity_support.class);
        startActivity(intent);
    }
    public void enter1 (View v){
        Intent intent = new Intent(this, activity_registration.class);
        hname = (TextView) findViewById(R.id.product_name);
        intent.putExtra("hname", hname.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Hotels> options = new FirebaseRecyclerOptions.Builder<Hotels>()
                .setQuery(HotelsRef, Hotels.class).build();

        FirebaseRecyclerAdapter<Hotels, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Hotels, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int i, @NonNull @NotNull Hotels model) {
                holder.txtProductName.setText(model.getCountry()+" " + model.getHname());
                holder.txtProductDescription.setText(model.getDescription());
                holder.txtProductPrice.setText("Стоимость = " + model.getPrice() + " рублей");
                Picasso.get().load(model.getImage()).into(holder.imageView);
            }

            @NonNull
            @NotNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_items_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}