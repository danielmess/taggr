package taggr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class User {
    private String userName;
    private Set<Photo> photoSet = new HashSet();
    private Set<String> userTags = new HashSet();

    public User() {
    }

    public void addUser(String userName) {
        this.userName = userName;
    }

    public void addPhoto() {
        System.out.println("Please enter the photo's URL and press Enter.");
        Scanner input = new Scanner(System.in);
        String photoURL = input.nextLine();
        System.out.println("Please enter your caption/description and press Enter.");
        String photoDescription = input.nextLine();
        System.out.println("Please enter all tags you'd like the photo to have, separated by a comma and a space. Hit enter when finished");
        String tagsString = input.nextLine();
        String[] tagsArray = tagsString.split(", ");
        Set<String> tagsSet = new HashSet(Arrays.asList(tagsArray));
        this.photoSet.add(new Photo(photoURL, photoDescription, tagsSet));
        String[] var7 = tagsArray;
        int var8 = tagsArray.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            String tag = var7[var9];
            this.userTags.add(tag);
        }

    }
}