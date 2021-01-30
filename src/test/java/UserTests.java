import org.junit.Assert;
import org.junit.Test;
import taggr.Photo;
import taggr.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserTests {

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
    @Test
    public void test_add_overlapping_tags_and_get_expected_tagset_Size(){
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
        sutUser.deletePhoto(sutPhoto2);
        int tagOccurrence = sutUser.getTagsIndex().get("a");
        //Assert
        Assert.assertEquals(1, tagOccurrence);
    }
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
}

