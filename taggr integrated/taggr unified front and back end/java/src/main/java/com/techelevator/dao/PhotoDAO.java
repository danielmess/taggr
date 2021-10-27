package com.techelevator.dao;

import com.techelevator.model.Photo;
import com.techelevator.model.Tag;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface PhotoDAO {
    public void createNewPhotoAndAddToUserSQL(String photoURL, String photoDescription, Set<Tag> tagsSet, User user);

    public void deletePhotoFromUserSQL (long photoID, User user);

    public void updatePhotoNewDescriptionSQL(long photoID, String newDescription, User user);

    public Photo retrieveUserPhotoFromURLSQL (String url, User user);

    public Photo retrieveUserPhotoFromPhotoID(long photoId, User user);

    public String retrieveUserPhotoURLFromPhotoId (long photoId, User user);

    public void addTagToPhotoSQL (String url, String tag, User user);

    public void deleteTagFromPhotoSQL (String url, String tag, User user);

    public List<Photo> listUserPhotosBySearchTagSQL(String tag, User user);

    public List<Photo>  listUserPhotosBySearchDescSQL(String keyword, User user);

    public List<Photo> listAllUserPhotosFromSQL(User user);

    public Photo mapRowtoPhoto(SqlRowSet rowSet);
}
