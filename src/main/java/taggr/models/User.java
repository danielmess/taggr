package taggr.models;

import taggr.models.Photo;

import java.util.*;

//Class notes - as I learn about persistence and security, I anticipate User private instance variables will expand
//to include first name, last name, email, password, etc. Or this might be implemented via SQL? Unclear at the moment.

public class User {
    //set private instance variables
    private String userName;
    private long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String passwordhashed;


    private Map<String, Photo> photoSet = new HashMap<>(); //photoSet uses the photo's URL as its key value, meaning that
    //a user couldn't accidentally add the same photo twice.
    //Sanitation is important for sanity!

    //all of the User's tags are stored as a set.
    private Set<Tag> userTags = new HashSet();

    //index with key of tag and value of # of tag occurrences in user's photoSet
    private Map<Tag, Integer> tagsIndex = new HashMap<>();

    //set blank constructor
    public User() {
    }

    //set constructor with userName as sole parameter
    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, long user_id){
        this.userName = userName;
        this.user_id = user_id;
    }

    //set fully parameterized constructor
    public User (String userName, String password, String first_name, String last_name, String email){
        this.userName = userName;
        this.passwordhashed = passwordhashed;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    //setters
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setFirst_Name(String first_name) {this.first_name = first_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setEmail(String email) { this.email = email; }
    public void setUser_id(long user_id) { this.user_id = user_id;}

    public void setPhotoSet(Map<String, Photo> photoSet) { this.photoSet = photoSet;}

    public void setUserTags(Set<Tag> userTags) { this.userTags = userTags; }

    public void setTagsIndex(Map<Tag, Integer> tagsIndex) {this.tagsIndex = tagsIndex;}

    //getters


    public String getEmail() {
        return email;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Photo> getPhotoSet() {
        return photoSet;
    }

    public Set<Tag> getUserTags() {
        return userTags;
    }

    public Map<Tag, Integer> getTagsIndex() {
        return tagsIndex;
    }

    //set User methods

    //adds an existing Photo to User's photoSet Map and its tags to User's userTags Set. Updates tagsIndex.
    public void addExistingPhotoToUser(Photo newPhoto) {
        //add photo to PhotoSet for user
        String url = newPhoto.getPhotoURL();
        photoSet.put(url, newPhoto);
        //add newPhoto's tags to user's userTags Set.
        Set<Tag> tagsToAdd = newPhoto.getTags();
        addTagsToUserTagsAndTagsIndex(tagsToAdd);
    }

    //creates a new Photo and adds it to User's photoSet Map and its tags to User's userTags Set. updates tagsIndex.
    public void createNewPhotoAndAddToUser(String photoURL, String photoDescription, Set<Tag> tagsSet) {
        //create photo, add to photoSet for this user
        this.photoSet.put(photoURL, new Photo(photoURL, photoDescription, tagsSet));
        //add photo to PhotoSet for user
        String url = photoURL;
        //add newPhoto's tags to user's userTags Set:
        //add the photos tags to user's userTags Set and update tagsIndex:
        addTagsToUserTagsAndTagsIndex(tagsSet);
    }

    public void deletePhoto(Photo photoToDelete) {
        //set photoKey to use in appropriate maps
        String photoKey = photoToDelete.getPhotoURL();
        //get set of tag Strings contained in Photo to be deleted.
        Set<Tag> tagsToDelete = photoSet.get(photoKey).getTags();
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
    public void printUserPhotosTagSearch(Tag tagToSearchFor) {
        int photoNumber = 0;
        for (Map.Entry<String, Photo> entry : photoSet.entrySet()) {
            Photo photoToPrint = entry.getValue();
            String URL = entry.getKey();
            String description = entry.getValue().getPhotoDescription();
            Set photoTags = entry.getValue().getTags();
            String tagsAsString = entry.getValue().getTagsAsString();
            if (isTagInUserTags(tagToSearchFor)) {
                photoNumber++;
                System.out.println("Photo #" + photoNumber + " has a URL of '" + URL + "', a description of '" + description +
                        "', and its tags are '" + tagsAsString + "'.");
            }
        }
    }

    //prints off a list of a user's photos and their attendant information if their description contains given string
    public void printUserPhotosDescriptionSearch(String keyword) {
        int photoNumber = 0;
        for (Map.Entry<String, Photo> entry : photoSet.entrySet()) {
            Photo photoToPrint = entry.getValue();
            String URL = entry.getKey();
            String description = entry.getValue().getPhotoDescription();
            String tagsAsString = entry.getValue().getTagsAsString();
            if (description.contains(keyword)) {
                photoNumber++;
                System.out.println("Photo #" + photoNumber + " has a URL of '" + URL + "', a description of '"
                        + description + "', and its tags are '" + tagsAsString + "'.");
            }
        }
    }

    //prints off a list of all tags in a user's photos and how many photos each tag appears on
    public void printUserTagsIndex() {
        //set tag number to add in reading list
        int tagNumber = 0;
        //for-each loop to iterate through each tag - tag occurrence key-value pair in the tagsIndex map
        for (Map.Entry<Tag, Integer> entry : tagsIndex.entrySet()) {
            //increments tagNumber by one for each entry
            tagNumber++;
            //setting tag and tagOccurrence as the entry's key and value respectively to make code review easier.
            Tag theTag = entry.getKey();
            String tagName = theTag.getTagName();;
            Integer tagOccurrence = entry.getValue();
            //prints out tag info.
            System.out.println("Tag #" + tagNumber + " is '" + tagName + "' and it occurs "
                    + tagOccurrence + " times in this User's photoset.");
        }
    }

    //getter to put all of the user's tags in a single comma-delimited string.
    public String getUserTagsAsString() {
        String tagsPrintout = "";
        for (Tag tag : userTags) {
            tagsPrintout += tag.getTagName() + ", ";
        }
        //strips final ", " off the final string
        String userTagsListWithCommas = tagsPrintout.substring(0, tagsPrintout.length() - 2);
        return userTagsListWithCommas;
    }


    //adds a tag to an existing Photo for the user
    public void addTagToPhoto(String url, Tag tag) {
        //do some gets to make it easier to see what's happening
        Photo photoToEdit = photoSet.get(url);
        Set photoTagsToEdit = photoToEdit.getTags();
        //add new tag to Photo's tags Set
        photoTagsToEdit.add(tag);
        addTagToUserTagsAndTagsIndex(tag);
    }

    //rewrites description for a user's given Photo
    public void rewritePhotoDescription(String url, String newDescription) {
        Photo photoToEdit = photoSet.get(url);
        //set new description
        photoToEdit.setPhotoDescription(newDescription);
    }

    //deletes a tag from a user's given Photo
    public void deleteTagFromPhoto(String url, Tag tagToDelete){
        Photo photoToEdit = photoSet.get(url);
        photoToEdit.getTags().remove(tagToDelete);
        deleteTagFromUserTagsAndTagsIndex(tagToDelete);

    }

    public void addPhotoToUser(String url, String photoDescription, String tagsString) {
        //make tagsString into an array of tags as Strings
        String[] tagsArray = tagsString.split(", ");
        Set<Tag> tagsToAdd = new HashSet(Arrays.asList(tagsArray));
        //creates new Photo and adds it to photoSet Map as a Value with photoURL as the unique key
        this.photoSet.put(url, new Photo(url, photoDescription, tagsToAdd));
        // adds new Photo's tag Strings to the userTags String Set and updates tags index.
        addTagsToUserTagsAndTagsIndex(tagsToAdd);
    }

    public void deletePhotoFromUser(String urlToDelete) {
        String photoKey = urlToDelete;
        //get set of tag Strings contained in Photo to be deleted.
        Set<Tag> tagsToDelete = photoSet.get(photoKey).getTags();
        //run delete tags submethod
        deleteTagsFromIndexAndUserTags(tagsToDelete);
        //removes photo from photoSet
        photoSet.remove(photoKey);
    }




    //submethods

    //adds all tags to the user's userTags Set<String> and updates the user's tagsIndex as needed.
    public void addTagsToUserTagsAndTagsIndex(Set<Tag> tagsToAdd){
        for (Tag tag : tagsToAdd) {
            addTagToUserTagsAndTagsIndex(tag);
        }
    }

    //adds a single tag to the user's tagsIndex and userTags if new, or updates the tagsIndex if pre-existing.
    public void addTagToUserTagsAndTagsIndex(Tag tagToAdd){
        //updates tag in User's tagsIndex; if tag already exists, increases value by 1. if tag doesn't already
        // exist, puts tag into tagsIndex and give it an initial occurrence value of 1.
        Boolean tagInUserTags = isTagInUserTags(tagToAdd);
        //puts the tagToAdd in the tagsIndex with a val of 1 if it's not in there already, adds to userTags
        if (!tagInUserTags){
            tagsIndex.put(tagToAdd, 1);
            userTags.add(tagToAdd);
        } else {
            //if tagToAdd is already in tags Index, updates the value to be one less.
            for(Tag tagToUpdate: userTags){
                if (tagToUpdate.getTagName().equalsIgnoreCase(tagToAdd.getTagName())){
                    int tagcount = tagsIndex.get(tagToUpdate);
                    tagcount++;
                    tagsIndex.replace(tagToUpdate, tagcount);

                }
            }
        }

    }

    //updates user's tagsIndex and deletes from tagsIndex and userTags if needed.
    public void deleteTagsFromIndexAndUserTags(Set<Tag> tagsToDelete) {
        //set for-each loop to run through each tag n do delete tag method
        for (Tag tag : tagsToDelete) {
            deleteTagFromUserTagsAndTagsIndex(tag);
        }
    }

    //updates a single tag and deletes if necessary from tagsIndex and userTags.
    public void deleteTagFromUserTagsAndTagsIndex(Tag tagToDelete){
        //gets the proper tag from Tags Index
        Tag theTag = findTagInUserTags(tagToDelete);
        if(isTagInUserTags(tagToDelete)) {
            if (tagsIndex.get(theTag) == 1) {
                //if tag only occurs once, deletes the tag from tagsIndex and userTags
                tagsIndex.remove(theTag);
                userTags.remove(theTag);
            } else {
                //if tag occurs more than once, reduces its occurrence value by one.
                Integer tagOccurrence = tagsIndex.get(theTag);
                tagsIndex.replace(theTag, tagOccurrence - 1);
            }
        }
    }

    public boolean isTagInUserTags(Tag tagToFind){
        Boolean tagInUserTags = false;
        for (Tag tag: userTags){
            if(tag.getTagName().equalsIgnoreCase(tagToFind.getTagName())){
                tagInUserTags = true;
                break;
            }
        }
        return tagInUserTags;
    }

    public boolean isTagStringInUserTags(String tagNameToFind){
        Boolean tagInUserTags = false;
        for (Tag tag: userTags){
            if(tag.getTagName().equalsIgnoreCase(tagNameToFind)){
                tagInUserTags = true;
                break;
            }
        }
        return tagInUserTags;
    }

    public Tag findTagInUserTagsFromString(String tagName){
        Tag foundTag = new Tag();
        for(Tag tag: userTags){
            if(tag.getTagName().equalsIgnoreCase(tagName)){
                foundTag = tag;
                break;
            }
        }
        return foundTag;
    }

    public Tag findTagInUserTags(Tag tagToFind){
        Tag foundTag = new Tag();
        for(Tag tag: userTags){
            if(tag.getTagName().equalsIgnoreCase(tagToFind.getTagName())){
                foundTag = tag;
                break;
            }
        }
        return foundTag;
    }
}