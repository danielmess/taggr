package taggr.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import taggr.dao.PhotoDAO;
import taggr.dao.TagDAO;
import taggr.dao.UserDAO;
import taggr.models.Photo;
import taggr.models.Tag;
import taggr.models.TagDTO;
import taggr.models.User;

import javax.sql.DataSource;
import java.util.*;

@Component
public class JDBCuserDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private PhotoDAO daoPhoto;
    private TagDAO daoTag;

    public JDBCuserDAO(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }



    /**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     * @param userName the user name to give the new user
     * @param password the user's password
     * @return the new user
     */
    @Override
    public User saveUser(String userName, String password, String first_name, String last_name, String email) {
        return null;
    }
    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    @Override
    public boolean isUsernameAndPasswordValid(String userName, String password) {
        return false;
    }

    @Override
    //mapped in SQL file
    public Map<Tag, Integer> getUserTagsIndexSQL(int user_id) {
        Map<Tag, Integer> userTagsIndex = new HashMap<>();
        String userTagsIndexQuery = "SELECT tags.tag, COUNT (photo_and_tag_relation.photo_id) AS tag_count " +
         "FROM photo_and_tag_relation " + "INNER JOIN tags ON photo_and_tag_relation.tag_id = tags.tag_id " +
         "WHERE tags.user_id = ? " + "GROUP BY tags.tag ";

        return userTagsIndex;
    }

    @Override
    //mapped  in SQL file
    public Set<Tag> getUserTagsSQL(int user_id) {
        Set<Tag> userTags = new HashSet<>();
        String userTagsQuery = "SELECT tag FROM tags WHERE user_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(userTagsQuery, user_id);
        while (results.next()){
            Tag theTag = mapRowToTag(results);
            userTags.add(theTag);
        }
        return userTags;
    }

    @Override
    //mapped in SQL file
    public void displayUserPhotoSQL() {

    }





    private User mapRowToUser(SqlRowSet results){
        User theUser = new User();
        theUser.setUserName(results.getString("username"));
        theUser.setFirst_Name(results.getString("first_name"));
        theUser.setEmail(results.getString("email"));
        theUser.setUser_id(results.getInt("user_id"));

        return theUser;
    }

    private Tag mapRowToTag(SqlRowSet results){
        String theTagName = results.getString("tag");
        Tag theTag = new Tag();
        theTag.setTagName(theTagName);
        return theTag;
    }

//    private String mapRowToTagIndexPair(SqlRowSet results){
//        //hmmmm - how to save a key-value pair
//        String theTagName = results.getString("tag");
//        Tag theTag = new Tag();
//        theTag.setTagName(theTagName);
//        String tagCount = results.getString("tag_count");
//
//    }

}
