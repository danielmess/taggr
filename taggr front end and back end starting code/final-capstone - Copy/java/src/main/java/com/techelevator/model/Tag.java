package com.techelevator.model;

public class Tag {

    private String tagName= "";
    private long tagID;
    private long userID;

    public Tag(){}

    public Tag(String tagName, long tagID, long userID){
        this.tagName = tagName;
        this.tagID = tagID;
        this.userID = userID;

    }

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
}
