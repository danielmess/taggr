package com.techelevator.model;

public class TagDTO {
    private String tagName= "";
    private long tagID;
    private long userID;
    private long photoID;

    public TagDTO(){}

    public TagDTO(String tagName, long tagID, long userID, long photoID){
        this.tagName = tagName;
        this.tagID = tagID;
        this.userID = userID;
        this.photoID = photoID;

    }

    public long getPhotoID(){ return photoID;}

    public long getTagID() {
        return tagID;
    }

    public String getTagName() { return tagName;
    }

    public long getUserID() {
        return userID;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagID(long tagID) {
        this.tagID = tagID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setPhotoID(long photoID) { this.photoID = photoID; }
}
