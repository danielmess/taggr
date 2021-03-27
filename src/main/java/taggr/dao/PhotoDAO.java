package taggr.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import taggr.models.Photo;
import taggr.models.Tag;
import taggr.models.User;

import java.util.List;
import java.util.Set;

public interface PhotoDAO {

    public void createNewPhotoAndAddToUserSQL(String photoURL, String photoDescription, Set<Tag> tagsSet, User user);

    public void deletePhotoFromUserSQL (String photourl, User user);

    public void givePhotoNewDescriptionSQL (String photourl, String newDescription, User user);

    public Photo retrieveUserPhotoFromURLSQL (String url, User user);

    public void addTagToPhotoSQL (String url, String tag, User user);

    public void deleteTagFromPhotoSQL (String url, String tag, User user);

    public List<Photo>  listUserPhotosBySearchTagSQL(String tag, User user);

    public List<Photo>  listUserPhotosBySearchDescSQL(String keyword, User user);

    public List<Photo> listAllUserPhotosFromSQL(User user);

    public Photo mapRowtoPhoto(SqlRowSet rowSet);

}
