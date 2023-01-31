package com.example.freezone;

import android.widget.Button;

public class modalClass {
    private int image1;
    private String name;
    private String Price_name;


    public   modalClass(int image1, String name, String price_name) {
        this.image1 = image1;
        this.name = name;
       this.Price_name = price_name;

    }
    public int getImage1() {
        return image1;
    }

    public String getName() {
        return name;
    }

    public String getPrice_name() {
        return Price_name;

    }

}
