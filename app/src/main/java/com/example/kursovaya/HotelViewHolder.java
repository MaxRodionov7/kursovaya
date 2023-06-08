package com.example.kursovaya;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtHotelName, txtHotelDescription, txtHotelPrice;
    public ImageView imageView;
    public ItemClickListner listner;

    public Button loginBtn;

    public HotelViewHolder(View itemView)
    {
        super(itemView);


        imageView = itemView.findViewById(R.id.product_image);
        txtHotelName = itemView.findViewById(R.id.product_name);
        txtHotelDescription = itemView.findViewById(R.id.product_description);
        txtHotelPrice = itemView.findViewById(R.id.product_price);
        loginBtn = itemView.findViewById(R.id.login_btn);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
