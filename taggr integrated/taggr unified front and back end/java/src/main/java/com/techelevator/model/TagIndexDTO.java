package com.techelevator.model;

public class TagIndexDTO implements Comparable<TagIndexDTO>{
    private String tagName = "";
    private long tag_Id;
    private long user_Id;
    private int count;

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTag_Id(long tag_Id) {
        this.tag_Id = tag_Id;
    }

    public void setUser_Id(long user_Id) {
        this.user_Id = user_Id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getUser_Id() {
        return user_Id;
    }

    public String getTagName() {
        return tagName;
    }

    public long getTag_Id() {
        return tag_Id;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(TagIndexDTO ti) {
        return this.getTagName().compareTo(ti.getTagName().toLowerCase());
    }
}
