package taggr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Photo {
    private String photoURL;
    private String photoDescription;
    private Set<String> photoTagsSet = new HashSet();

    //set blank constructor
    public Photo(){}

    //set parametized constructor
    public Photo(String photoURL, String photoDescription, Set tags) {
        this.photoURL = photoURL;
        this.photoDescription = photoDescription;
        this.photoTagsSet = tags;
    }

// set setters - no setter for tags; instead we do a specific method to Add tags to the tags Set.
// also we don't set the URL for a photo because the URL is the key to the Photo in the User's Map photoset.

    public void setUrl(String photoURL) { // this is only used in testing suite
        this.photoURL = photoURL;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    // set getters
    public String getUrl() {
        return this.photoURL;
    }

    public String getPhotoDescription() {
        return this.photoDescription;
    }

    public Set getTags(){
        return photoTagsSet;
    }

    public String getTagsAsString() { //returns tags as a comma-delimited String.
        String tagsPrintout = "";
        if (photoTagsSet.size() > 0) {
            for (String tag : photoTagsSet) {
                tagsPrintout += tag + ", ";
            }
            //strips final ", " off
            String tagsListWithCommas = tagsPrintout.substring(0, tagsPrintout.length() - 2);
            return tagsListWithCommas;
        }
        return "";
    }

    //set methods

    public void setTags(List<String> tagList) { //set tags from a given List of Strings

        for (String tag: tagList) {
            photoTagsSet.add(tag);
        }
    }

    public void setTagsFromString(String tagsAsString){ //set tags from a single comma-delimited String
        String[] tagsArray = tagsAsString.split(", ");
        for(String tag: tagsArray){
            this.photoTagsSet.add(tag);
        }
    }

    //prints out the designated photo's url, description, and tags as a single String.
    public void printPhotoInfo(){
        System.out.println("The photo's url is '" + getUrl() + "' and the description is '" + getPhotoDescription() +
                "' and the photo's tags are '" + getTagsAsString() + "'.");
    }

    public void printTags() { //print tags to Console
        String tagsPrintout = "";
        for(String tag: photoTagsSet) {
            tagsPrintout += tag + ", ";
        }
        String tagsListWithCommas = tagsPrintout.substring(0,tagsPrintout.length()-2);
        System.out.println("The tags for this photo are '" + tagsListWithCommas + "'.");

    }

    public void deleteTagsFromString(String tagsAsString){ //delete tags from a single comma-delimited String
        String[] tagsArray = tagsAsString.split(", ");
        for(String tag: tagsArray){
            this.photoTagsSet.remove(tag);
        }
    }

    //delete single tag from a Photo's tagsSet
    public void deleteTag(String tag) {
        this.photoTagsSet.remove(tag);
    }


}
