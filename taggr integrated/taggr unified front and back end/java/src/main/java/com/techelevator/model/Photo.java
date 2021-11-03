package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Photo {

    private String url;
    private String description;
    private long user_id;
    private List<Tag> photoTagsList = new ArrayList<>();
    private long photo_id;

    //set blank constructor
    public Photo(){}

    //set parameterized constructor
    public Photo(String url, String description, List<Tag> tagList) {
        this.url = url;
        this.description = description;
        this.photoTagsList = tagList;
    }

    // setters

    public void setUrl(String url) { // this is only used in testing suite
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoTagsList(List<Tag> photoTagsList) {
        this.photoTagsList = photoTagsList;
    }

    public void setPhoto_Id(long photo_Id) { this.photo_id = photo_Id; }

    public void setUser_id(long user_id) { this.user_id = user_id; }

    // getters
    public String getUrl() {
        return this.url;
    }

    public String getDescription() {
        return this.description;
    }

    public long getPhoto_Id() { return photo_id;}

    public long getUser_id() { return user_id; }

    public List<Tag> getTags(){ return photoTagsList; }
}
