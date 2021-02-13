package taggr;

import java.util.*;

//Class notes - as I learn about persistence and security, I anticipate User private instance variables will expand
//to include first name, last name, email, password, et cetera. Or this might be implemented via SQL? Unclear at the moment.

public class User {
    //set private instance variables
    private String userName;
    private Map<String, Photo> photoSet = new HashMap<>(); //photoSet uses the photo's URL as its key value, meaning that
    //a user couldn't accidentally add the same photo twice.
    //Sanitation is important for sanity!

    //all of the User's tags are stored as a set.
    private Set<String> userTags = new HashSet();

    //index with key of tag and value of # of tag occurrences in user's photoSet
    private Map<String, Integer> tagsIndex = new HashMap<>();

    //set blank constructor
    public User() {
    }

    //set constructor with userName as sole parameter
    public User(String userName) {
        this.userName = userName;
    }

    //set setters - no setter for photoSet, userTags, or tagsIndex because those should *only* be affected by adding Photos
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //set vanilla getters

    public String getUserName() {
        return userName;
    }

    public Map<String, Photo> getPhotoSet() {
        return photoSet;
    }

    public Set<String> getUserTags() {
        return userTags;
    }

    public Map<String, Integer> getTagsIndex() {
        return tagsIndex;
    }

    //set User methods

    //adds an existing Photo to User's photoSet Map and its tags to User's userTags Set. Updates tagsIndex.
    public void addExistingPhotoToUser(Photo newPhoto) {
        //add photo to PhotoSet for user
        String url = newPhoto.getUrl();
        photoSet.put(url, newPhoto);
        //add newPhoto's tags to user's userTags Set.
        Set<String> tagsToAdd = newPhoto.getTags();
        addTagsToUserTagsAndTagsIndex(tagsToAdd);
    }

    //creates a new Photo and adds it to User's photoSet Map and its tags to User's userTags Set. updates tagsIndex.
    public void createNewPhotoandAddToUser(String photoURL, String photoDescription, Set<String> tagsSet) {
        //create photo, add to photoSet for this user
        this.photoSet.put(photoURL, new Photo(photoURL, photoDescription, tagsSet));
        //add photo to PhotoSet for user
        String url = photoURL;
        //add newPhoto's tags to user's userTags Set:
        Set<String> tagsToAdd = photoSet.get(photoURL).getTags(); //photoSet.get(PhotoURL) returns the Value, which for
        //photoSet is a Photo Object. calling .getTags() on that Photo returns the String Set of tags
        //add the photos tags to user's userTags Set and update tagsIndex:
        addTagsToUserTagsAndTagsIndex(tagsToAdd);
    }

    public void deletePhoto(Photo photoToDelete) {
        //set photoKey to use in appropriate maps
        String photoKey = photoToDelete.getUrl();
        //get set of tag Strings contained in Photo to be deleted.
        Set<String> tagsToDelete = photoSet.get(photoKey).getTags();
        //run delete tags submethod
        deleteTagsFromIndexAndUserTags(tagsToDelete);
        //removes photo from photoSet
        photoSet.remove(photoKey);
    }

    //prints off a list of a user's photos and their attendant information.
    public void printUserPhotosInfo() {
        int photoNumber = 0;
        for (Map.Entry<String, Photo> entry : photoSet.entrySet()) {
            Photo photoToPrint = entry.getValue();
            String URL = entry.getKey();
            String description = entry.getValue().getPhotoDescription();
            String tags = entry.getValue().getTagsAsString();
            photoNumber++;
            //make this a method??
            System.out.println("Photo #" + photoNumber + " has a URL of '" + URL + "', a description of '" + description +
                    "', and its tags are '" + tags + "'.");

        }
    }

    //prints off a list of a user's photos and their attendant information if they have a given tag
    public void printUserPhotosTagSearch(String tag) {
        int photoNumber = 0;
        for (Map.Entry<String, Photo> entry : photoSet.entrySet()) {
            Photo photoToPrint = entry.getValue();
            String URL = entry.getKey();
            String description = entry.getValue().getPhotoDescription();
            String tags = entry.getValue().getTagsAsString();
            if (tags.contains(tag)) {
                photoNumber++;
                System.out.println("Photo #" + photoNumber + " has a URL of '" + URL + "', a description of '" + description +
                        "', and its tags are '" + tags + "'.");
            }
        }
    }

    //prints off a list of a user's photos and their attendant information if their description contains given string
    public void printUserPhotosDescriptionSearch(String descriptionsearch) {
        int photoNumber = 0;
        for (Map.Entry<String, Photo> entry : photoSet.entrySet()) {
            Photo photoToPrint = entry.getValue();
            String URL = entry.getKey();
            String description = entry.getValue().getPhotoDescription();
            String tags = entry.getValue().getTagsAsString();
            if (description.contains(descriptionsearch)) {
                photoNumber++;
                System.out.println("Photo #" + photoNumber + " has a URL of '" + URL + "', a description of '"
                        + description + "', and its tags are '" + tags + "'.");
            }
        }
    }

    //prints off a list of all tags in a user's photos and how many photos each tag appears on
    public void printUserTagsIndex() {
        //set tag number to add in reading list
        int tagNumber = 0;
        //for-each loop to iterate through each tag - tag occurrence key-value pair in the tagsIndex map
        for (Map.Entry<String, Integer> entry : tagsIndex.entrySet()) {
            //increments tagNumber by one for each entry
            tagNumber++;
            //setting tag and tagOccurrence as the entry's key and value respectively to make code review easier.
            String tag = entry.getKey();
            Integer tagOccurrence = entry.getValue();
            //prints out tag info.
            System.out.println("Tag #" + tagNumber + " is '" + tag + "' and it occurs "
                    + tagOccurrence + " times in this User's photoset.");
        }
    }

    //getter to put all of the user's tags in a single comma-delimited string.
    public String getUserTagsAsString() {
        String tagsPrintout = "";
        for (String tag : userTags) {
            tagsPrintout += tag + ", ";
        }
        //strips final ", " off the final string
        String userTagsListWithCommas = tagsPrintout.substring(0, tagsPrintout.length() - 2);
        return userTagsListWithCommas;
    }

    //adds a tag to an existing Photo for the user
    public void addTagToPhoto(String url, String tag) {
        //do some gets to make it easier to see what's happening
        Photo photoToEdit = photoSet.get(url);
        Set photoTagstoEdit = photoToEdit.getTags();
        //add new tag to Photo's tags Set
        photoTagstoEdit.add(tag);
        //updates tag in User's tagsIndex; if tag already exists, increases value by 1. if tag doesn't already
        // exist, puts tag into tagsIndex and give it an initial occurrence value of 1.
        if (tagsIndex.containsKey(tag)) {
            Integer tagOccurrence = tagsIndex.get(tag);
            tagsIndex.replace(tag, tagOccurrence, tagOccurrence + 1);
        } else {
            tagsIndex.put(tag, 1);
        }
    }

    //rewrites description for a user's given Photo
    public void rewritePhotoDescription(String url, String newDescription) {
        Photo photoToEdit = photoSet.get(url);
        //set new description
        photoToEdit.setPhotoDescription(newDescription);
    }

    //deletes a tag from a user's given Photo
    public void deleteTagFromPhoto(String url, String tagToDelete){
        Photo photoToEdit = photoSet.get(url);
        photoToEdit.getTags().remove(tagToDelete);
        //now to delete the tag from the user's userTags Set and tagsIndex Map if it only occurs once; otherwise we leave
        //userTags alone and decrease the tag's value by one in the tagsIndexMap.
        if (tagsIndex.get(tagToDelete) == 1) {
            //if tag only occurs once, deletes the tag from tagsIndex and userTags
            tagsIndex.remove(tagToDelete);
            userTags.remove(tagToDelete);
        } else {
            //if tag occurs more than once, reduces its occurrence value by one and leaves the tag
            // in userTags alone.
            Integer tagOccurrence = tagsIndex.get(tagToDelete);
            tagsIndex.replace(tagToDelete, tagOccurrence - 1);
        }
    }

    public void addPhotoToUser(String url, String photoDescription, String tagsString) {
        //make tagsString into an array of tags as Strings
        String[] tagsArray = tagsString.split(", ");
        Set<String> tagsToAdd = new HashSet(Arrays.asList(tagsArray));
        //creates new Photo and adds it to photoSet Map as a Value with photoURL as the unique key
        this.photoSet.put(url, new Photo(url, photoDescription, tagsToAdd));
        // adds new Photo's tag Strings to the userTags String Set and updates tags index.
        addTagsToUserTagsAndTagsIndex(tagsToAdd);
    }

    public void deletePhotoFromUser(String urlToDelete) {
        String photoKey = urlToDelete;
        //get set of tag Strings contained in Photo to be deleted.
        Set<String> tagsToDelete = photoSet.get(photoKey).getTags();
        //run delete tags submethod
        deleteTagsFromIndexAndUserTags(tagsToDelete);
        //removes photo from photoSet
        photoSet.remove(photoKey);
    }




    //submethods

    //adds all tags to the users's userTags Set<String> and updates the user's tagsIndex.
    public void addTagsToUserTagsAndTagsIndex(Set<String> tagsToAdd){
        // adds new Photo's tag Strings to the userTags String Set.
        for (String tag : tagsToAdd) {
            userTags.add(tag);
        }
        //updates user's tagsIndex
        for (String tag : tagsToAdd) {
            addTagToTagsIndex(tag);
        }
    }

    //adds a single tag to the user's tagsIndex and userTags if new, or updates the tagsIndex if pre-existing.
    public void addTagToTagsIndex(String tag){
        //updates tag in User's tagsIndex; if tag already exists, increases value by 1. if tag doesn't already
        // exist, puts tag into tagsIndex and give it an initial occurrence value of 1.
        if (tagsIndex.containsKey(tag)) {
            Integer tagOccurrence = tagsIndex.get(tag);
            tagsIndex.replace(tag, tagOccurrence, tagOccurrence + 1);
        } else {
            tagsIndex.put(tag, 1);
        }
    }

    //updates user's tagsIndex and deletes from tagsIndex and userTags if needed.
    public void deleteTagsFromIndexAndUserTags(Set<String> tagsToDelete) {
        //set for-each loop to run through each tag n do delete tag method
        for (String tag : tagsToDelete) {
            deleteTagFromTagsIndex(tag);
        }
    }

    //updates a single tag and deletes if necessary from tagsIndex and userTags.
    public void deleteTagFromTagsIndex(String tag){
        if (tagsIndex.get(tag) == 1) {
            //if tag only occurs once, deletes the tag from tagsIndex and userTags
            tagsIndex.remove(tag);
            userTags.remove(tag);
        } else {
            //if tag occurs more than once, reduces its occurrence value by one.
            Integer tagOccurrence = tagsIndex.get(tag);
            tagsIndex.replace(tag, tagOccurrence - 1);
        }
    }

}