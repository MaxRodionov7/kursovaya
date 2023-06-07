package com.example.kursovaya;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtHotelName, txtHotelDescription, txtHotelPrice;
    public ImageView imageView;
    public ItemClickListner listner;

    public HotelViewHolder(View itemView)
    {
        super(itemView);


        imageView = itemView.findViewById(R.id.product_image);
        txtHotelName = itemView.findViewById(R.id.product_name);
        txtHotelDescription = itemView.findViewById(R.id.product_description);
        txtHotelPrice = itemView.findViewById(R.id.product_price);
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
