package com.example.freezone;

public class userRegistration {
    public String email, phone,passowrd,confirmPassword;
    //declaring constructor
    public userRegistration(){

    }

    public userRegistration(String email, String phone, String passowrd, String confirmPassword) {
        this.email = email;
        this.phone = phone;
        this.passowrd = passowrd;
        this.confirmPassword = confirmPassword;
    }
}
