package taggr.models;

import java.util.HashSet;
import java.util.List;
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

    public String getTagsAsString() { //returns tags as a comma-delimited String.
        String tagsAsString = "";
        if (photoTagsSet.size() > 0) {
            for (Tag tag : photoTagsSet) {
                tagsAsString += tag.getTagName() + ", ";
            }
            //strips final ", " off
            String tagsListWithCommas = tagsAsString.substring(0, tagsAsString.length() - 2);
            return tagsListWithCommas;
        } else {
            return tagsAsString;
        }
    }

    //set methods

    public void setTags(List<String> tagList) { //set tags from a given List of Strings

        for (String tag: tagList) {
            Tag theTag = new Tag();
            theTag.setTagName(tag);
            photoTagsSet.add(theTag);
        }
    }

    public void setTagsFromString(String tagsAsString){ //set tags from a single comma-delimited String
        String[] tagsArray = tagsAsString.split(", ");
        for(String tag: tagsArray){
            Tag theTag = new Tag();
            theTag.setTagName(tag);
            this.photoTagsSet.add(theTag);
        }
    }

    //prints out the designated photo's url, description, and tags as a single String.
    public void printPhotoInfo(){
        System.out.println("The photo's url is '" + getPhotoURL() + "' and the description is '" + getPhotoDescription() +
                "' and the photo's tags are '" + getTagsAsString() + "'.");
    }

    public void printTags() { //print tags to Console
        String tagsPrintout = "";
        for(Tag tag: photoTagsSet) {
            tagsPrintout += tag.getTagName() + ", ";
        }
        String tagsListWithCommas = tagsPrintout.substring(0,tagsPrintout.length()-2);
        System.out.println("The tags for this photo are '" + tagsListWithCommas + "'.");

    }



    //delete single tag from a Photo's tagsSet
    public void deleteTag(Tag tag) {
        this.photoTagsSet.remove(tag);
    }

    //delete tags from a comma-delimited list from a Photo's photoTagsSet
    public void deleteTagsFromString(String tagsAsString){ //delete tags from a single comma-delimited String
        String[] tagsArray = tagsAsString.split(", ");
        for(String tag: tagsArray){
            for(Tag theTag: photoTagsSet){
                if (theTag.getTagName().equalsIgnoreCase(tag)){
                    photoTagsSet.remove(theTag);
                }
            }
        }
    }

    //locates the Tag in a Photo's photoTagsSet
    public Tag findTagInPhotoTagsFromString(String tagName){
        Tag foundTag = new Tag();
        for(Tag tag: photoTagsSet){
            if(tag.getTagName().equalsIgnoreCase(tagName)){
                foundTag = tag;
                break;
            }
        }
        return foundTag;
    }

    public boolean isTagInPhotoTags(Tag tagToFind){
        Boolean tagInUserTags = false;
        for (Tag tag: photoTagsSet){
            if(tag.getTagName().equalsIgnoreCase(tagToFind.getTagName())){
                tagInUserTags = true;
                break;
            }
        }
        return tagInUserTags;
    }

    public boolean isTagStringInPhotoTags(String tagNameToFind){
        Boolean tagInUserTags = false;
        for (Tag tag: photoTagsSet){
            if(tag.getTagName().equalsIgnoreCase(tagNameToFind)){
                tagInUserTags = true;
                break;
            }
        }
        return tagInUserTags;
    }
}
