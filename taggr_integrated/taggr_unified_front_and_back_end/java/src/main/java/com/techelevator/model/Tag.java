package com.techelevator.model;

public class Tag {

    private String tag_name= "";
    private long tag_id;
    private long user_id;


    public Tag(){}

    public Tag(String tagName, long tagID, long userID){
        this.tag_name = tagName;
        this.tag_id = tagID;
        this.user_id = userID;

    }

    public long getTag_Id() {
        return tag_id;
    }

    public String getTag_Name() { return tag_name;
    }

    public long getUser_Id() {
        return user_id;
    }

    public void setTag_Name(String tagName) {
        this.tag_name = tagName;
    }

    public void setTag_Id(long tagID) {
        this.tag_id = tagID;
    }

    public void setUser_Id(long userID) {
        this.user_id = userID;
    }
}

