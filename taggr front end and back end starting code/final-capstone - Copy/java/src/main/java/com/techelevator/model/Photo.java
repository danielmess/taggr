package com.techelevator.model;

import java.util.HashSet;
import java.util.Set;

public class Photo {

    private String photoURL;
    private String photoDescription;
    private long user_id;
    private Set<Tag> photoTagsSet = new HashSet();
    private long photoID;

    //set blank constructor
    public Photo(){}

    //set parametized constructor
    public Photo(String photoURL, String photoDescription, Set tagsSet) {
        this.photoURL = photoURL;
        this.photoDescription = photoDescription;
        this.photoTagsSet = tagsSet;
    }

// setters

    public void setPhotoURL(String photoURL) { // this is only used in testing suite
        this.photoURL = photoURL;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public void setPhotoTagsSet(Set<Tag> photoTagsSet) {
        this.photoTagsSet = photoTagsSet;
    }

    public void setPhotoID(long photoID) { this.photoID = photoID; }

    public void setUser_id(long user_id) { this.user_id = user_id; }

    // set getters
    public String getPhotoURL() {
        return this.photoURL;
    }

    public String getPhotoDescription() {
        return this.photoDescription;
    }

    public long getPhotoID() { return photoID;}

    public long getUser_id() { return user_id; }

    public Set getTags(){
        return photoTagsSet;
    }
}
