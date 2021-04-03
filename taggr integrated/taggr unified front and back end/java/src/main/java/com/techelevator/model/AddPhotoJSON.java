package com.techelevator.model;

public class AddPhotoJSON {
    private String photoURL;
    private String description;
    private String tagsCSV;

    public String getPhotoURL() {
        return photoURL;
    }

    public String getDescription() {
        return description;
    }

    public String getTagsCSV() {
        return tagsCSV;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTagsCSV(String tagsCSV) {
        this.tagsCSV = tagsCSV;
    }
}
