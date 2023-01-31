package com.example.freezone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {
    ArrayList<productModal>list;
    Context context;
    //declaring constructor
    public productAdapter(ArrayList<productModal>list, Context context){
        this.list=list;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //imlementing the layout of our design
        View view= LayoutInflater.from(context).inflate(R.layout.activity_myproduct, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        productModal modal=list.get(position);
        Picasso.get().load(modal.getPhoto()).placeholder(R.drawable.pc).into(holder.productImage);
        holder.description.setText((modal.getDescription()));
        holder.productName.setText((modal.getProductName()));
        holder.productCategory.setText((modal.getCategory()));
        holder.productPrice.setText((modal.getPrice()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //declaring the id's from the product layout
        TextView description, productName, productCategory,productPrice;
        ImageView productImage;


    public ViewHolder(@NonNull View itemView) {

        super(itemView);
        description= itemView.findViewById(R.id.description);
        productName= itemView.findViewById(R.id.productName);
        productCategory= itemView.findViewById(R.id.productCategory);
        productPrice= itemView.findViewById(R.id.productPrice);
        productImage= itemView.findViewById(R.id.productImage);


    }
}




}
