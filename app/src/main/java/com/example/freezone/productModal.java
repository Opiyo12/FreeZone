package com.example.freezone;

import android.widget.TextView;

public class productModal {
    private String headline, description, price, category, productName;
    private String photo;

    public productModal() {
    }

    public productModal(String headline, String description, String price, String category, String productName, String photo) {
        this.headline = headline;
        this.description = description;
        this.price = price;
        this.category = category;
        this.productName = productName;
        this.photo = photo;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
