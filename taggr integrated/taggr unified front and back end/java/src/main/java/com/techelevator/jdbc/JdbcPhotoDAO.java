package com.techelevator.jdbc;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.TagDAO;
import com.techelevator.model.Photo;
import com.techelevator.model.Tag;
import com.techelevator.model.TagDTO;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JdbcPhotoDAO implements PhotoDAO {
    private JdbcTemplate jdbcTemplate;
    private TagDAO tagDAO;

    public  JdbcPhotoDAO(DataSource dataSource, TagDAO tagDAO) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.tagDAO = tagDAO;
    }

    @Override
    public List<Photo> listUserPhotosBySearchTagSQL(String tag, User user) {
        List<Photo> userPhotos = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT photo_and_tag_relation.photo_id, photos.user_id, photos.url, photos.description " +
                " FROM photo_and_tag_relation" + " INNER JOIN photos ON photo_and_tag_relation.photo_id = photos.photo_id" +
                " WHERE photos.photo_id IN (" + " SELECT photo_and_tag_relation.photo_id FROM photo_and_tag_relation" +
                " INNER JOIN tags ON tags.tag_id = photo_and_tag_relation.tag_id " + "WHERE tags.user_id = ? AND tags.tag_name ILIKE '%' || ? || '%' )";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getId(), tag);
        while(results.next()){
            Photo thePhoto = mapRowtoPhoto(results);
            userPhotos.add(thePhoto);
        }


        userPhotos = getTagsForPhotoList(userPhotos, user);
        return userPhotos;
    }

    @Override
    public List<Photo>  listUserPhotosBySearchDescSQL(String keyword, User user) {
        List<Photo> userPhotos = new ArrayList<>();
        String sqlQuery = "SELECT photo_id, user_id, url, description FROM photos WHERE user_id = ? AND description LIKE '%' || ? || '%' ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getId(), keyword);
        while(results.next()){
            Photo thePhoto = mapRowtoPhoto(results);
            userPhotos.add(thePhoto);
        }

        userPhotos = getTagsForPhotoList(userPhotos, user);
        return userPhotos;
    }

    @Override
    public List<Photo> listAllUserPhotosFromSQL(User user){
        long userID = user.getId();
        List<Photo> userPhotos = new ArrayList<>();

        //get base photos without tags from photos table
        String sqlQuery = "SELECT * FROM photos WHERE user_id= ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userID);
        while(results.next()){
            Photo thePhoto = mapRowtoPhoto(results);
            userPhotos.add(thePhoto);
        }

        userPhotos = getTagsForPhotoList(userPhotos, user);

        return userPhotos;
    }

    @Override
    public void createNewPhotoAndAddToUserSQL(String photoURL, String photoDescription, Set<Tag> tagsSet, User user) {

        Photo photoToAdd = new Photo(photoURL, photoDescription, tagsSet);
        String photoToAddSQL = "INSERT INTO photos (user_id, url, description) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;";

        //inserts new Photo into SQL
        jdbcTemplate.update(photoToAddSQL, user.getId(), photoToAdd.getUrl(), photoToAdd.getDescription());

        //get new photo's newly made ID
        String sqlnewphotoquery = "SELECT photo_id, user_id, url, description FROM photos WHERE url = ? AND user_id = ?";
        SqlRowSet photoIDresult = jdbcTemplate.queryForRowSet(sqlnewphotoquery, photoURL, user.getId());
        List<Photo> newPhotos = new ArrayList<>();
        while(photoIDresult.next()){
            Photo thePhoto = mapRowtoPhoto(photoIDresult);
            newPhotos.add(thePhoto);
        }
        Photo sqlPhoto = newPhotos.get(0);
        photoToAdd.setPhoto_Id(sqlPhoto.getPhoto_Id());

        //upsert photo's tags into tags table.
        for (Tag tag : tagsSet) {
            String sqlTagUpsert = "INSERT INTO tags (tag_name, user_id) VALUES (?, ?) ON CONFLICT DO NOTHING;";
            jdbcTemplate.update(sqlTagUpsert, tag.getTag_Name(), user.getId());
        }
        //get updated Set of UserTags from database tags table.
        Set<Tag> userTags = tagDAO.findUserTags(user.getId());
        //cycle through photo's tags
        for(Tag photoTag: tagsSet){
            //cycle through userTags
            for(Tag userTag: userTags){
                if (photoTag.getTag_Name().equalsIgnoreCase(userTag.getTag_Name())){
                    //inserts Tag ID into the tag in photo's tagset
                    photoTag.setTag_Id(userTag.getTag_Id());
                }
            }
            //insert photo's tag with its new tag_id and the photo's photo_id into photo_tag_relation table
            String sqlTagPhotoUpsert = "INSERT INTO photo_and_tag_relation (photo_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING;";
            jdbcTemplate.update(sqlTagPhotoUpsert, photoToAdd.getPhoto_Id(), photoTag.getTag_Id());
        }

    }

    @Override
    public void deletePhotoFromUserSQL (long photoID, User user){
        //get user id
        Long user_id = user.getId();
        //check to ensure photo belongs to user; if so, proceed with deletion.
        String sqlPhotoCheck = "SELECT * FROM photos WHERE photo_id = ? AND user_id = ?";
        SqlRowSet photoCheck = jdbcTemplate.queryForRowSet(sqlPhotoCheck, photoID, user_id);
        List<Photo> photoToVerify = new ArrayList<>();
        while(photoCheck.next()){
            Photo thePhoto = mapRowtoPhoto(photoCheck);
            photoToVerify.add(thePhoto);
        }
        Photo sqlPhoto = photoToVerify.get(0);
        if(sqlPhoto.getPhoto_Id() == photoID && sqlPhoto.getUser_id() == user_id){
        //delete photo from photo table and photo and tag relation table
        String sqlDeleteTransaction = "BEGIN TRANSACTION; " + " DELETE FROM photo_and_tag_relation WHERE photo_id = ?;" +
                "DELETE FROM photos WHERE photo_id = ? AND user_id = ? ;" + " COMMIT;";
        jdbcTemplate.update(sqlDeleteTransaction, photoID, photoID, user_id);
        //cleanup tags for user that are no longer on photo_and_tag_relation
        String sqlTagCleanupTransaction = "DELETE FROM tags WHERE user_id = ? AND tag_id NOT IN "+
                " (SELECT photo_and_tag_relation.tag_id " + "FROM photo_and_tag_relation) ;";
        jdbcTemplate.update(sqlTagCleanupTransaction, user_id);}
    }

    @Override
    public void updatePhotoNewDescriptionSQL(String photoURL, String newDescription, User user){
        String sqlUpdatePhotoDesc = "UPDATE photos SET description = ? WHERE user_id = ? AND url = ? ;";
        jdbcTemplate.update(sqlUpdatePhotoDesc, newDescription, user.getId(), photoURL);
    }

    @Override
    public String retrieveUserPhotoURLFromPhotoId (long photoId, User user){
        String photoQuery = "SELECT url from photos WHERE user_id = ? AND  photo_id = ? ;";
        return jdbcTemplate.queryForObject(photoQuery, String.class, user.getId(), photoId);
    }


    @Override
    public Photo retrieveUserPhotoFromURLSQL (String url, User user){
        List<Photo> photoList = new ArrayList<>();
        String sqlPhotoQuery = "SELECT photo_id, user_id, url, description FROM photos" +
                " WHERE user_id = ? AND url = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPhotoQuery, user.getId(), url);
        while(results.next()){
            Photo thePhoto = mapRowtoPhoto(results);
            photoList.add(thePhoto);
        }
        photoList = getTagsForPhotoList(photoList, user);
        Photo thePhoto = photoList.get(0);
        return thePhoto;
    }

    @Override
    public void addTagToPhotoSQL (String url, String tag, User user){
        Photo thePhoto = retrieveUserPhotoFromURLSQL(url, user);
        long photoID = thePhoto.getPhoto_Id();
        String sqlUpdate = "INSERT INTO tags (tag_name, user_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sqlUpdate, tag, user.getId());
        Tag newTag = tagDAO.findTagByName(tag, user);
        long tagID = newTag.getTag_Id();
        String sqlTagInsert ="INSERT INTO photo_and_tag_relation (photo_id, tag_id) VALUES (?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sqlTagInsert, photoID, tagID);
    }

    @Override
    public void deleteTagFromPhotoSQL (String url, String tag, User user){
        Photo thePhoto = retrieveUserPhotoFromURLSQL(url, user);
        long photoID = thePhoto.getPhoto_Id();
        Tag tagToDelete = tagDAO.findTagByName(tag, user);
        long tagID = tagToDelete.getTag_Id();
        String sqlDelete = "DELETE FROM photo_and_tag_relation WHERE tag_id = ? AND photo_id = ? ;";
        jdbcTemplate.update(sqlDelete, tagID, photoID);
        String sqlTagCleanupTransaction = "DELETE FROM tags WHERE user_id = ? AND tag_id NOT IN "+
                " (SELECT photo_and_tag_relation.tag_id " + "FROM photo_and_tag_relation) ;";
        jdbcTemplate.update(sqlTagCleanupTransaction, user.getId());
    }

    private List<Photo> getTagsForPhotoList(List<Photo> photoList, User user){
        long userID = user.getId();
        //get user's TagDTOs
        List<TagDTO> userTagDTOs = new ArrayList<>();
        String sqlQueryTags ="Select photo_and_tag_relation.photo_id, photo_and_tag_relation.tag_id, tags.tag_name, tags.user_id"+
                " FROM photo_and_tag_relation INNER JOIN tags ON tags.tag_id = photo_and_tag_relation.tag_id"+
                " WHERE tags.user_id = ? ;";
        SqlRowSet tagDAOresults = jdbcTemplate.queryForRowSet(sqlQueryTags, userID);
        while(tagDAOresults.next()){
            TagDTO theTagDTO = tagDAO.mapRowToTagDTO(tagDAOresults);
            userTagDTOs.add(theTagDTO);
        }
        //cycle through each photo
        for (Photo thePhoto: photoList){
            //cycle through each TagDTO - if it matches the PhotoID of thePhoto, converts theTagDTO into newTag,
            //then adds to Photo's TagSet
            for(TagDTO theTagDTO: userTagDTOs){
                if(theTagDTO.getPhotoID() == thePhoto.getPhoto_Id()){
                    Tag newTag = tagDAO.convertTagDTOtoTag(theTagDTO);
                    Set<Tag> photoTags = thePhoto.getTags();
                    photoTags.add(newTag);
                    thePhoto.setPhotoTagsSet(photoTags);
                }
            }
        }

        return photoList;

    }

    @Override
    public Photo mapRowtoPhoto(SqlRowSet rowSet){
        Photo thePhoto = new Photo();
        thePhoto.setPhoto_Id(rowSet.getLong("photo_id"));
        thePhoto.setUser_id(rowSet.getLong("user_id"));
        thePhoto.setUrl(rowSet.getString("url"));
        thePhoto.setDescription(rowSet.getString("description"));
        return thePhoto;
    }

}
