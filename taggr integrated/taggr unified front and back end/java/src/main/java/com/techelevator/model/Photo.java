package com.techelevator.model;

import java.util.HashSet;
import java.util.Set;

public class Photo {

    private String url;
    private String description;
    private long user_id;
    private Set<Tag> photoTagsSet = new HashSet();
    private long photo_id;

    //set blank constructor
    public Photo(){}

    //set parametized constructor
    public Photo(String url, String description, Set tagsSet) {
        this.url = url;
        this.description = description;
        this.photoTagsSet = tagsSet;
    }

// setters

    public void setUrl(String url) { // this is only used in testing suite
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoTagsSet(Set<Tag> photoTagsSet) {
        this.photoTagsSet = photoTagsSet;
    }

    public void setPhoto_Id(long photo_Id) { this.photo_id = photo_Id; }

    public void setUser_id(long user_id) { this.user_id = user_id; }

    // set getters
    public String getUrl() {
        return this.url;
    }

    public String getDescription() {
        return this.description;
    }

    public long getPhoto_Id() { return photo_id;}

    public long getUser_id() { return user_id; }

    public Set getTags(){
        return photoTagsSet;
    }
}
