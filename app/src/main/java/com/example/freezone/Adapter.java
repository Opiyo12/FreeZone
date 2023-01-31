package com.example.freezone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    //declaring the variable of the modal class as list
    private List<modalClass> userlist;
    //method for initialising the list
    public Adapter(List<modalClass> userlist) {
        this.userlist=userlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //implementing the design of our layout file
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //this method binds the data from our main activity to insside our layout with recyclerview
    //we declare different variabes with a different name from those in the modal class
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //here userlist is used to set the data
        int resource =userlist.get(position).getImage1();
        String name1= userlist.get(position).getName();
        String itemprice=userlist.get(position).getPrice_name();
        // send the above data to a holder
        holder.setData(resource,name1,itemprice);


    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image1;
        private TextView name, price ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image1 =itemView.findViewById(R.id.image1);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }

        public void setData(int resource, String name1, String itemprice) {
            image1.setImageResource(resource);
            name.setText(name1);
            price.setText(itemprice);

        }
    }
}
