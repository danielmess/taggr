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
    public Photo(String photoURL, String photoDescription, Set tagsSet) {
        this.url = photoURL;
        this.description = photoDescription;
        this.photoTagsSet = tagsSet;
    }

// setters

    public void setPhotoURL(String photoURL) { // this is only used in testing suite
        this.url = photoURL;
    }

    public void setPhotoDescription(String photoDescription) {
        this.description = photoDescription;
    }

    public void setPhotoTagsSet(Set<Tag> photoTagsSet) {
        this.photoTagsSet = photoTagsSet;
    }

    public void setPhotoID(long photoID) { this.photo_id = photoID; }

    public void setUser_id(long user_id) { this.user_id = user_id; }

    // set getters
    public String getPhotoURL() {
        return this.url;
    }

    public String getPhotoDescription() {
        return this.description;
    }

    public long getPhotoID() { return photo_id;}

    public long getUser_id() { return user_id; }

    public Set getTags(){
        return photoTagsSet;
    }
}
