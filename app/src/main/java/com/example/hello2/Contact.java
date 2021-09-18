package com.example.hello2;

import androidx.annotation.NonNull;

public class Contact {
    private String name;
    private String email;
    private String phoneNumber;
    private String imageUrl;

    public Contact(String name, String email, String phoneNumber, String imageUrl) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return name ;
    }
}
