package taggr;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaggrScratch {
   //this is where I stick old methods, Just In Case. a GNDN Class.

    //set CL methods
//    public void addPhotoFromCommandLine() {
//        //get user input for photo URL, description, and tags
//        System.out.println("Please enter the photo's URL and press Enter.");
//        Scanner input = new Scanner(System.in);
//        String photoURL = input.nextLine();
//        System.out.println("Please enter your caption/description and press Enter.");
//        String photoDescription = input.nextLine();
//        System.out.println("Please enter all tags you'd like the photo to have, separated by a comma and a space. Hit enter when finished");
//        String tagsString = input.nextLine();
//        //make tagsString into an array of tags as Strings
//        String[] tagsArray = tagsString.split(", ");
//        Set<String> tagsToAdd = new HashSet(Arrays.asList(tagsArray));
//        //creates new Photo and adds it to photoSet Map as a Value with photoURL as the unique key
//        this.photoSet.put(photoURL, new Photo(photoURL, photoDescription, tagsToAdd));
//        // adds new Photo's tag Strings to the userTags String Set.
//        for (String tag : tagsToAdd) {
//            userTags.add(tag);
//        }
//        //updates user's tagsIndex - if tag is new, adds to tagsIndex with a value of 1;
//        //if tag already exists, increments the value by 1.
//        for (String tag : tagsToAdd) {
//            //checks to see if tagsIndex for User contains key already
//            if (tagsIndex.containsKey(tag)) {
//                //adds 1 to tag's value in tagsIndex if tag already exists
//                Integer tagOccurrence = tagsIndex.get(tag);
//                tagsIndex.replace(tag, tagOccurrence + 1);
//            } else {
//                //if tag does not already exist in user's tagsIndex, puts tag in as key with start val of 1.
//                tagsIndex.put(tag, 1);
//            }
//        }
//    }
//
//    public void deletePhotoFromCommandLine() {
//        //get user input for photo URL
//        System.out.println("Please enter the photo's URL and press Enter.");
//        Scanner input = new Scanner(System.in);
//        //sets key to use in appropriate maps.
//        String photoKey = input.nextLine();
//        //get set of tag Strings contained in Photo to be deleted.
//        Set<String> tagsToDelete = photoSet.get(photoKey).getTags();
//        //set for-each loop to run through each tag
//        for (String tag : tagsToDelete) {
//            //boolean test to see if each tag only occurs once in the tagsIndex
//            if (tagsIndex.get(tag) == 1) {
//                //if tag only occurs once, deletes the tag from tagsIndex and userTags
//                tagsIndex.remove(tag);
//                userTags.remove(tag);
//            } else {
//                //if tag occurs more than once, reduces its occurrence value by one.
//                Integer tagOccurrence = tagsIndex.get(tag);
//                tagsIndex.replace(tag, tagOccurrence - 1);
//            }
//        }
//        //removes photo from photoSet
//        photoSet.remove(photoKey);

    //set CL methods
//    public void addPhotoFromCommandLine() {
//        //get user input for photo URL, description, and tags
//        System.out.println("Please enter the photo's URL and press Enter.");
//        Scanner input = new Scanner(System.in);
//        String photoURL = input.nextLine();
//        System.out.println("Please enter your caption/description and press Enter.");
//        String photoDescription = input.nextLine();
//        System.out.println("Please enter all tags you'd like the photo to have, separated by a comma and a space. Hit enter when finished");
//        String tagsString = input.nextLine();
//        //make tagsString into an array of tags as Strings
//        String[] tagsArray = tagsString.split(", ");
//        Set<String> tagsToAdd = new HashSet(Arrays.asList(tagsArray));
//        //creates new Photo and adds it to photoSet Map as a Value with photoURL as the unique key
//        this.photoSet.put(photoURL, new Photo(photoURL, photoDescription, tagsToAdd));
//        // adds new Photo's tag Strings to the userTags String Set.
//
//        for (String tag : tagsToAdd) {
//            userTags.add(tag);
//        }
//        //updates user's tagsIndex - if tag is new, adds to tagsIndex with a value of 1;
//        //if tag already exists, increments the value by 1.
//        for (String tag : tagsToAdd) {
//            //checks to see if tagsIndex for User contains key already
//            if (tagsIndex.containsKey(tag)) {
//                //adds 1 to tag's value in tagsIndex if tag already exists
//                Integer tagOccurrence = tagsIndex.get(tag);
//                tagsIndex.replace(tag, tagOccurrence + 1);
//            } else {
//                //if tag does not already exist in user's tagsIndex, puts tag in as key with start val of 1.
//                tagsIndex.put(tag, 1);
//            }
//        }
//    }
//
//    public void deletePhotoFromCommandLine() {
//        //get user input for photo URL
//        System.out.println("Please enter the photo's URL and press Enter.");
//        Scanner input = new Scanner(System.in);
//        //sets key to use in appropriate maps.
//        String photoKey = input.nextLine();
//        //get set of tag Strings contained in Photo to be deleted.
//        Set<String> tagsToDelete = photoSet.get(photoKey).getTags();
//        //set for-each loop to run through each tag
//        for (String tag : tagsToDelete) {
//            //boolean test to see if each tag only occurs once in the tagsIndex
//            if (tagsIndex.get(tag) == 1) {
//                //if tag only occurs once, deletes the tag from tagsIndex and userTags
//                tagsIndex.remove(tag);
//                userTags.remove(tag);
//            } else {
//                //if tag occurs more than once, reduces its occurrence value by one.
//                Integer tagOccurrence = tagsIndex.get(tag);
//                tagsIndex.replace(tag, tagOccurrence - 1);
//            }
//        }
//        //removes photo from photoSet
//        photoSet.remove(photoKey);
//
//    }
//
//    //menus -run off of User class because all of these actions only make sense in the contest of a particular User.
//    public void addDeleteMenu() {
//        //set flag for add-delete menu while loop
//        boolean finishedAddDeleteMenuLoop = false;
//        //begin while loop
//        while (!finishedAddDeleteMenuLoop) {
//            System.out.println("Add/Delete Photos menu:");
//            System.out.println("1) Add a Photo to current User's photoset");
//            System.out.println("2) Delete a Photo from current User's photoset");
//            System.out.println("3) Exit to Main Menu");
//            System.out.println("Please select option number and press enter.");
//            Scanner input = new Scanner(System.in);
//            int addDeleteMenuChoice = Integer.parseInt(input.nextLine());
//            switch (addDeleteMenuChoice) {
//                case 1:
//                    addPhotoFromCommandLine();
//                    //addphoto method
//                    break;
//                case 2:
//                    //delete photo method;
//                    deletePhotoFromCommandLine();
//                    break;
//                case 3:
//                    System.out.println("Now exiting to main menu.");
//                    finishedAddDeleteMenuLoop = true;
//                    break;
//                default:
//                    System.out.println("Please choose one of the supported menu options.");
//            }
//        }
//    }
//
//    public void listPhotosMenu() {
//        //set flag for add-delete menu while loop
//        boolean finishedListPhotoMenuLoop = false;
//        //begin while loop
//        while (!finishedListPhotoMenuLoop) {
//            System.out.println("List Photos and Tags menu:");
//            System.out.println("1) List all photos from current user's photoset that have a given tag");
//            System.out.println("2) List all photos from current user's photoset that have a keyword in their description");
//            System.out.println("3) List all user tags and how often they occur in photoset");
//            System.out.println("4) List all user tags.");
//            System.out.println("5) List all user photos");
//            System.out.println("6) Exit to Main Menu");
//            System.out.println("Please select option number and press enter.");
//            Scanner input = new Scanner(System.in);
//            int listPhotoMenuChoice = Integer.parseInt(input.nextLine());
//            switch (listPhotoMenuChoice) {
//                case 1:
//                    System.out.println("taggr will bring back all photos that have your tag in their tags");
//                    System.out.println("Please input the keyword you want to search for in the photoset's tags and hit enter:");
//                    String tag = input.nextLine();
//                    printUserPhotosTagSearch(tag);
//                    break;
//                case 2:
//                    //list photos by description keyword method.
//                    System.out.println("taggr will bring back all photos that have your keyword in their description.");
//                    System.out.println("Please input the keyword you want to search for in the photoset's descriptions and hit enter:");
//                    String keyword = input.nextLine();
//                    printUserPhotosDescriptionSearch(keyword);
//                    break;
//                case 3:
//                    printUserTagsIndex();
//                    break;
//                case 4:
//                    System.out.println(getUserTagsAsString());
//                    break;
//                case 5:
//                    printUserPhotosInfo();
//                    break;
//                case 6:
//                    System.out.println("Now exiting to main menu.");
//                    finishedListPhotoMenuLoop = true;
//                    break;
//                default:
//                    System.out.println("Please choose one of the supported menu options.");
//            }
//        }
//    }
//
//
//    public void editPhotosMenu() {
//        //set flag for add-delete menu while loop
//        boolean finishedEditPhotoMenuLoop = false;
//        //begin while loop
//        while (!finishedEditPhotoMenuLoop) {
//            System.out.println("Edit Photo Information menu:");
//            System.out.println("1) give a Photo a new description");
//            System.out.println("2) add a tag to a photo");
//            System.out.println("3) delete a tag from a photo");
//            System.out.println("4) List all user tags.");
//            System.out.println("5) List all user tags with their number of occurrences in photoset.");
//            System.out.println("6) List all user photos");
//            System.out.println("7) Exit to Main Menu");
//            System.out.println("Please select option number and press enter.");
//            Scanner input = new Scanner(System.in);
//            int listPhotoMenuChoice = Integer.parseInt(input.nextLine());
//            switch (listPhotoMenuChoice) {
//                case 1:
//                    System.out.println("Please enter the photo's URL and press enter.");
//                    String url = input.nextLine();
//                    System.out.println("Please enter the new description for the photo and press enter.");
//                    String newDescription = input.nextLine();
//                    rewritePhotoDescription(url, newDescription);
//                    //give photo a new description
//                    break;
//                case 2:
//                    System.out.println("Please enter the photo's URL and press enter.");
//                    String tagUrl = input.nextLine();
//                    System.out.println("Please enter the new tag for the photo and press enter.");
//                    String newTag = input.nextLine();
//                    addTagToPhoto(tagUrl, newTag);
//                    //add a tag to a photo
//                    break;
//                case 3:
//                    System.out.println("Please enter the photo's URL and press enter.");
//                    String tagDeleteUrl = input.nextLine();
//                    System.out.println("Please enter the tag to delete from the photo and press enter.");
//                    String deleteTag = input.nextLine();
//                    deleteTagFromPhoto(tagDeleteUrl, deleteTag);
//                    //delete a tag from a photo
//                    break;
//                case 4:
//                    System.out.println(getUserTagsAsString());
//                    break;
//                case 5:
//                    printUserTagsIndex();
//                    break;
//                case 6:
//                    printUserPhotosInfo();
//                    break;
//                case 7:
//                    System.out.println("Now exiting to main menu.");
//                    finishedEditPhotoMenuLoop = true;
//                    break;
//                default:
//                    System.out.println("Please choose one of the supported menu options.");
//                    break;
//
//            }
//        }
//    }

    }


