import org.junit.Assert;
import org.junit.Test;
import taggr.Photo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhotoTests {

    @Test
    public void add_unparametized_photo_and_set_and_get_URL_and_description(){
        //arrange
        Photo sut = new Photo();
        sut.setUrl("http.testURL//.com");
        sut.setPhotoDescription("this is a test");
        //act
        String result = sut.getUrl();
        String result2 = sut.getPhotoDescription();
        //Assert
        Assert.assertEquals("http.testURL//.com", result);
        Assert.assertEquals("this is a test", result2);
    }

    @Test
    public void add_parametized_photo_and_get_url_and_description(){
        //Arrange
        Set<String> sutTags = new HashSet<>(Arrays.asList("a", "b", "c"));
        //Act
        Photo sut = new Photo("testURL", "description", sutTags);
        String result = sut.getUrl();
        String result2 = sut.getPhotoDescription();
        Set result3 = sut.getTags();
        Assert.assertEquals("testURL", result);
        Assert.assertEquals("description", result2);
        Assert.assertEquals(sutTags, result3);
    }

    @Test
    public void setTags_test_pass_list(){
        //arrange
        Photo sut = new Photo();
        List<String> sutTags = Arrays.asList("a", "b", "c");
        Set<String> sutTagsSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        //Act
        sut.setTags(sutTags);
        Set result = sut.getTags();
        //Assert
        Assert.assertEquals(sutTagsSet, result);
    }

    @Test
    public void setTags_test_pass_strings_comma_delimited(){
        //arrange
        Photo sut = new Photo();
        Set<String> sutTagsSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        //Act
        sut.setTagsFromString("a, b, c");
        Set result = sut.getTags();
        //Assert
        Assert.assertEquals(sutTagsSet, result);
    }

    @Test
    public void printTags_test(){
        //Arrange
        Set<String> sutTags = new HashSet<>(Arrays.asList("a", "b", "c"));
        //Act
        Photo sut = new Photo("testURL", "description", sutTags);
        sut.printTags();
    }

    @Test
    public void getTagsAsString_test(){
        //Arrange
        Set<String> sutTags = new HashSet<>(Arrays.asList("a", "b", "c"));
        Photo sut = new Photo("testURL", "description", sutTags);
        //Act
        String result = sut.getTagsAsString();
        //Assert
        Assert.assertEquals("a, b, c", result);

    }

}
