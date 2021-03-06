package taggr.dao.jdbc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import taggr.dao.PhotoDAO;
import taggr.dao.TagDAO;
import taggr.models.Photo;
import taggr.models.Tag;
import taggr.models.TagDTO;
import taggr.models.User;

import javax.sql.DataSource;

@Component
public class JDBCphotoDAO implements PhotoDAO {
    private JdbcTemplate jdbcTemplate;
    private TagDAO tagDAO;

    public JDBCphotoDAO(BasicDataSource dataSource, TagDAO tagDAO) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.tagDAO = tagDAO;
    }

    @Override
    public List<Photo>  listUserPhotosBySearchTagSQL(String tag, User user) {
        List<Photo> userPhotos = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT photo_and_tag_relation.photo_id, photos.user_id, photos.url, photos.description " +
                " FROM photo_and_tag_relation" + " INNER JOIN photos ON photo_and_tag_relation.photo_id = photos.photo_id" +
                " WHERE photos.photo_id IN (" + " SELECT photo_and_tag_relation.photo_id FROM photo_and_tag_relation" +
                " INNER JOIN tags ON tags.tag_id = photo_and_tag_relation.tag_id " + "WHERE tags.user_id = ? AND tags.tag ILIKE '%' || ? || '%' )";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUser_id(), tag);
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
//        String sqlQuery = "SELECT photo_and_tag_relation.photo_id, photos.url, photos.description " +
//                " FROM photo_and_tag_relation" + " INNER JOIN photos ON photo_and_tag_relation.photo_id = photos.photo_id" +
//                " WHERE photos.photo_id IN (" + " SELECT photo_id" +
//                " INNER JOIN tags on tags.tag_id = photo_and_tag_relation.tag_id " + "WHERE photos.user_id = ? AND photos.description ILIKE '%?%' )";

        String sqlQuery = "SELECT photo_id, user_id, url, description FROM photos WHERE user_id = ? AND description LIKE '%' || ? || '%' ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUser_id(), keyword);
        while(results.next()){
            Photo thePhoto = mapRowtoPhoto(results);
            userPhotos.add(thePhoto);
        }

        userPhotos = getTagsForPhotoList(userPhotos, user);
        return userPhotos;
    }

    @Override
    public List<Photo> listAllUserPhotosFromSQL(User user){
        long userID = user.getUser_id();
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
//        long newPhotoID = getNextPhotoID();
//        photoToAdd.setPhotoID(newPhotoID);

        String photoToAddSQL = "INSERT INTO photos (user_id, url, description) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;";
        //inserts new Photo into SQL


        jdbcTemplate.update(photoToAddSQL, user.getUser_id(), photoToAdd.getPhotoURL(), photoToAdd.getPhotoDescription());

        //get new photo's newly made ID
        String sqlnewphotoquery = "SELECT photo_id, user_id, url, description FROM photos WHERE url = ? AND user_id = ?";
        SqlRowSet photoIDresult = jdbcTemplate.queryForRowSet(sqlnewphotoquery, photoURL, user.getUser_id());
        List<Photo> newPhotos = new ArrayList<>();
        while(photoIDresult.next()){
            Photo thePhoto = mapRowtoPhoto(photoIDresult);
            newPhotos.add(thePhoto);
        }
        Photo sqlPhoto = newPhotos.get(0);
        photoToAdd.setPhotoID(sqlPhoto.getPhotoID());
        //upsert photo's tags into tags table.
        for (Tag tag : tagsSet) {
            String sqlTagUpsert = "INSERT INTO tags (tag, user_id) VALUES (?, ?) ON CONFLICT DO NOTHING;";
            jdbcTemplate.update(sqlTagUpsert, tag.getTagName(), user.getUser_id());
        }
        //get updated Set of UserTags.
        Set<Tag> userTags = tagDAO.findUserTags(user.getUser_id());
        //cycle through photo's tags
        for(Tag phototag: tagsSet){
            //cycle through userTags
            for(Tag usertag: userTags){
                if (phototag.equals(usertag)){
                    //inserts Tag ID into photo's tagset
                    phototag.setTagID(usertag.getTagID());
                }
            }
            //insert photo's tag with its new tag_id into photo-tag_relation table
            String sqlTagPhotoUpsert = "INSERT INTO photo_and_tag_relation (photo_id, tag_id) VALUES (?, ?) ON CONFLICT DO NOTHING;";
            jdbcTemplate.update(sqlTagPhotoUpsert, photoToAdd.getPhotoID(), phototag.getTagID());
        }

    }

        @Override
        public void deletePhotoFromUserSQL (String photourl, User user){
        //get photo's ID
            String sqlQuery = "SELECT photo_id, user_id, url, description FROM photos WHERE user_id = ? AND url = ? ;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUser_id(), photourl);
            List<Photo> photoToDeleteList = new ArrayList<>();
            while(results.next()){
                Photo thePhoto = mapRowtoPhoto(results);
                photoToDeleteList.add(thePhoto);
            }
            Photo photoToDelete = photoToDeleteList.get(0);
            long photoToDeleteId = photoToDelete.getPhotoID();
        //delete photo from photo table and photo and tag relation table
        String sqlDeleteTransaction = "BEGIN TRANSACTION; " + " DELETE FROM photo_and_tag_relation WHERE photo_id = ?;" +
                "DELETE FROM photos WHERE photo_id = ? ;" + " COMMIT;";
        jdbcTemplate.update(sqlDeleteTransaction, photoToDeleteId, photoToDeleteId);
        //cleanup tags for user that are no longer on photo_and_tag_relation
            String sqlTagCleanupTransaction = "DELETE FROM tags WHERE user_id = ? AND tag_id NOT IN "+
                    " (SELECT photo_and_tag_relation.tag_id " + "FROM photo_and_tag_relation) ;";
            jdbcTemplate.update(sqlTagCleanupTransaction, user.getUser_id());
        }

        @Override
        public void givePhotoNewDescriptionSQL (String photoURL, String newDescription, User user){
        String sqlUpdatePhotoDesc = "UPDATE photos SET description = ? WHERE user_id = ? AND url = ? ;";
        jdbcTemplate.update(sqlUpdatePhotoDesc, newDescription, user.getUser_id(), photoURL);
        }

        @Override
        public Photo retrieveUserPhotoFromURLSQL (String url, User user){
        List<Photo> photoList = new ArrayList<>();
           String sqlPhotoQuery = "SELECT photo_id, user_id, url, description FROM photos" +
                   " WHERE user_id = ? AND url = ?";
           SqlRowSet results = jdbcTemplate.queryForRowSet(sqlPhotoQuery, user.getUser_id(), url);
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
        long photoID = thePhoto.getPhotoID();
        String sqlUpdate = "INSERT INTO tags (tag, user_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sqlUpdate, tag, user.getUser_id());
        Tag newTag = tagDAO.findTagByName(tag, user);
        long tagID = newTag.getTagID();
        String sqlTagInsert ="INSERT INTO photo_and_tag_relation (photo_id, tag_id) VALUES (?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sqlTagInsert, photoID, tagID);
    }

        @Override
        public void deleteTagFromPhotoSQL (String url, String tag, User user){
            Photo thePhoto = retrieveUserPhotoFromURLSQL(url, user);
            long photoID = thePhoto.getPhotoID();
            Tag tagToDelete = tagDAO.findTagByName(tag, user);
            long tagID = tagToDelete.getTagID();
            String sqlDelete = "DELETE FROM photo_and_tag_relation WHERE tag_id = ? AND photo_id = ? ;";
            jdbcTemplate.update(sqlDelete, tagID, photoID);
            String sqlTagCleanupTransaction = "DELETE FROM tags WHERE user_id = ? AND tag_id NOT IN "+
                    " (SELECT photo_and_tag_relation.tag_id " + "FROM photo_and_tag_relation) ;";
            jdbcTemplate.update(sqlTagCleanupTransaction, user.getUser_id());
        }

    private List<Photo> getTagsForPhotoList(List<Photo> photoList, User user){
        long userID = user.getUser_id();
        //get user's TagDTOs
        List<TagDTO> userTagDTOs = new ArrayList<>();
        String sqlQueryTags ="Select photo_and_tag_relation.photo_id, photo_and_tag_relation.tag_id, tags.tag, tags.user_id"+
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
                if(theTagDTO.getPhotoID() == thePhoto.getPhotoID()){
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
        thePhoto.setPhotoID(rowSet.getLong("photo_id"));
        thePhoto.setUser_id(rowSet.getLong("user_id"));
        thePhoto.setPhotoURL(rowSet.getString("url"));
        thePhoto.setPhotoDescription(rowSet.getString("description"));
        return thePhoto;
        }

//        private long getNextPhotoID () {
//            SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('photo_id') FROM photos");
//            if (nextIdResult.next()) {
//                return nextIdResult.getLong(1);
//            } else {
//                throw new RuntimeException("Something went wrong while getting an id for the new Photo");
//            }
//        }


}


