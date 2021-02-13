import org.junit.Assert;
import org.junit.Test;
import taggr.Photo;
import taggr.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserTests {

    //tests adding two existing photos to a new User and verifies that User has 2 photos
    @Test
    public void test_add_2_existing_photos_to_user(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        //Act
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        int setSize = sutUser.getPhotoSet().size();
        //Assert
        Assert.assertEquals(2, setSize);
    }
    //adds two photos with overlapping tag sets and verifies that User's tagSet is as expected.
    @Test
    public void test_add_overlapping_tags_and_get_expected_tagSet_Size(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        //Act
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        int setSize = sutUser.getUserTags().size();
        //Assert
        Assert.assertEquals(4, setSize);
    }

    //verifies deleting a photo from a User leaves User with expected number of photos
    @Test
    public void test_add_2_existing_photos_to_user_then_delete(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        //Act
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        sutUser.deletePhoto(sutPhoto);
        int setSize = sutUser.getPhotoSet().size();
        int tagSize = sutUser.getUserTags().size();
        //Assert
        Assert.assertEquals(1, setSize);
        //Assert.assertEquals(3, tagSize);
    }
    //tests adding a photo to user and that photoSet and number of tags are correct
    @Test
    public void test_add_new_photo_to_user(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        //Act
        sutUser.createNewPhotoandAddToUser("testurl", "test description", sutSet);
        int setSize = sutUser.getPhotoSet().size();
        int tagSize = sutUser.getUserTags().size();
        //Assert
        Assert.assertEquals(1, setSize);
        Assert.assertEquals(3, tagSize);
    }

    //tests printing a list of user's photos and their info to the console.
        @Test
    public void test_print_user_photos_info(){
        //Arrange
            User sutUser = new User("tester");
            Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
            Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
            Photo sutPhoto = new Photo("testurl", "test description", sutSet);
            Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
            sutUser.addExistingPhotoToUser(sutPhoto);
            sutUser.addExistingPhotoToUser(sutPhoto2);
            //Act
            sutUser.printUserPhotosInfo();

        }
    //tests getting user tags as a String
    @Test
    public void test_get_user_tags_asString(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        //Act
        System.out.println(sutUser.getUserTagsAsString());

    }
//  unable to test CommandLine methods at the moment
//    @Test
//    public void test_add_photo_from_CL(){
//        //Arrange
//        User sutUser = new User("tester");
//        sutUser.addPhotoFromCommandLine();
//        //Act
//        int sutPhotoSetsize = sutUser.getPhotoSet().size();
//        //
//        Assert.assertEquals(1,sutPhotoSetsize);
//    }

    //tests usage of user's tagIndex - does it update properly as new / existing tags are applied?
    @Test
    public void test_add_2_existing_photos_to_user_and_check_tag_occurrence(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        //Act
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        int tagOccurrence = sutUser.getTagsIndex().get("a");
        //Assert
        Assert.assertEquals(2, tagOccurrence);
    }

    //tests if deleting a user's photo will update their tagIndex appropriately
    @Test
    public void test_add_2_existing_photos_to_user_delete_one_and_check_tag_occurrence(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        //Act
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        sutUser.deletePhotoFromUser("testurl2");
        int tagOccurrence = sutUser.getTagsIndex().get("a");
        //Assert
        Assert.assertEquals(1, tagOccurrence);
    }
    //tests printing the tag index for a user, indicating how many tags they have in their tagIndex
    //and how often each tag appears in a user's photoSet.
    @Test
    public void test_add_2_existing_photos_to_user_print_tags(){
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        //Act
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        //Assert
       sutUser.printUserTagsIndex();
    }

    @Test
    public void test_add_3_existing_photos_to_user_print_based_on_tags() {
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Set<String> sutSet3 = new HashSet<>(Arrays.asList("a", "d", "e"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        Photo sutPhoto3 = new Photo("testurl3", "test description3", sutSet3);
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        sutUser.addExistingPhotoToUser(sutPhoto3);
        //Act
        sutUser.printUserPhotosTagSearch("b");
    }

    @Test
    public void test_add_3_existing_photos_to_user_print_based_on_description() {
        //Arrange
        User sutUser = new User("tester");
        Set<String> sutSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> sutSet2 = new HashSet<>(Arrays.asList("a", "d", "c"));
        Set<String> sutSet3 = new HashSet<>(Arrays.asList("a", "d", "e"));
        Photo sutPhoto = new Photo("testurl", "test description", sutSet);
        Photo sutPhoto2 = new Photo("testurl2", "test description2", sutSet2);
        Photo sutPhoto3 = new Photo("testurl3", "test description3", sutSet3);
        sutUser.addExistingPhotoToUser(sutPhoto);
        sutUser.addExistingPhotoToUser(sutPhoto2);
        sutUser.addExistingPhotoToUser(sutPhoto3);
        //Act
        sutUser.printUserPhotosDescriptionSearch("2");
    }
}

