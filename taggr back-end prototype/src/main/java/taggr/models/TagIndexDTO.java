package taggr.models;

public class TagIndexDTO {
    private String tagName = "";
    private long tagID;
    private long userID;
    private int count;

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagID(long tagID) {
        this.tagID = tagID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getUserID() {
        return userID;
    }

    public String getTagName() {
        return tagName;
    }

    public long getTagID() {
        return tagID;
    }

    public int getCount() {
        return count;
    }
}
