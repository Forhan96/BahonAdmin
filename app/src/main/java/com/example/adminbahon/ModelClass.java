package com.example.adminbahon;

public class ModelClass {
    private String imageResource;
    private String title;
    private String body;
    private String user;

    public ModelClass(String imageResource, String title, String body, String uid) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUser() {return user; }
}
