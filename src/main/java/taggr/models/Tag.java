package taggr.models;


import java.util.HashSet;
import java.util.Set;

//implement tag class / tag dao / tag jdbc - this will help with making web application run more smoothly!
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



    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Tag)){
            return false;
        }
        Tag t =(Tag)o;
        return tagName.equalsIgnoreCase(t.getTagName())&&
                Long.compare(userID, t.getUserID())==0;
    }
}
