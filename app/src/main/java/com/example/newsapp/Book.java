package com.example.newsapp;


public class Book {
    private String title;
    private String authorName;
    private String description;
    private String imageUri;
    private String buyLink;

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return imageUri;
    }

    public void setImage(String image) {
        this.imageUri = image;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public Book(String name, String authorName, String description, String imageUri, String buyLink) {
        this.title = name;
        this.authorName = authorName;
        this.description = description;
        this.buyLink=buyLink;
        this.imageUri = imageUri;
    }
}
