package com.techelevator.jdbc;

import com.techelevator.dao.TagDAO;
import com.techelevator.model.Tag;
import com.techelevator.model.TagDTO;
import com.techelevator.model.TagIndexDTO;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JdbcTagDAO implements TagDAO {


    private JdbcTemplate jdbcTemplate;

    public void JDBCtagDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Set<Tag> findUserTags(long userID){
        Set<Tag> userTags = new HashSet<>();
        String sqlUserTagQuery = "SELECT * FROM tags WHERE user_id = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlUserTagQuery, userID);
        while(results.next()){
            Tag theTag = mapRowToTag(results);
            userTags.add(theTag);
        }
        return userTags;
    }

    @Override
    public Set<TagDTO> findUserTagDTOs(long userID){
        Set<TagDTO> userTagDTOs = new HashSet<>();
        String sqlUserTagDTOquery = "SELECT tag_id, tag, user_id, photo_and_tag_relation.photo_id FROM tags INNER JOIN photo_and_tag_relation ON tags.tag_id = photo_and_tag_relation.photo_id WHERE user_id = ? ;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlUserTagDTOquery, userID);
        while(results.next()){
            TagDTO theTagDTO = mapRowToTagDTO(results);
            userTagDTOs.add(theTagDTO);
        }
        return userTagDTOs;
    }

    @Override
    public Set<Tag> createTagsSetFromCSV(String tagscsv, User user){
        String[] tagnames = tagscsv.split(",");
        Set<Tag> photoTags = new HashSet<>();
        for(String tagname: tagnames){
            Tag theTag = new Tag();
            String trimmedTagName = tagname.trim();
            theTag.setTagName(trimmedTagName);
            theTag.setUserID(user.getId());
            photoTags.add(theTag);
        }
        return photoTags;
    }

    @Override
    public List<TagIndexDTO> getUserTagIndex(User user){
        String sqlTagIndexQuery = "SELECT tags.tag_id, tags.tag, user_id, COUNT (photo_and_tag_relation.photo_id) AS count "+
                "FROM photo_and_tag_relation INNER JOIN tags on tags.tag_id = photo_and_tag_relation.tag_id "+
                "WHERE user_id = ? "+
                "GROUP BY tags.tag_id, tags.tag, user_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTagIndexQuery, user.getId());
        List<TagIndexDTO> indexResults = new ArrayList<>();
        while(results.next()){
            TagIndexDTO theTagIndexDTO = mapRowToTagIndexDTO(results);
            indexResults.add(theTagIndexDTO);
        }
        return indexResults;
    }

    @Override
    public Tag mapRowToTag(SqlRowSet rowSet){
        Tag theTag = new Tag();
        theTag.setTagID(rowSet.getLong("tag_id"));
        theTag.setTagName(rowSet.getString("tag"));
        theTag.setUserID(rowSet.getLong("user_id"));
        return theTag;
    }

    @Override
    public TagDTO mapRowToTagDTO(SqlRowSet rowSet){
        TagDTO theTagDTO = new TagDTO();
        theTagDTO.setPhotoID(rowSet.getLong("photo_id"));
        theTagDTO.setTagID(rowSet.getLong("tag_id"));
        theTagDTO.setTagName(rowSet.getString("tag"));
        theTagDTO.setUserID(rowSet.getLong("user_id"));
        return theTagDTO;
    }

    @Override
    public Tag convertTagDTOtoTag(TagDTO tagDTO){
        Tag theTag = new Tag();
        theTag.setUserID(tagDTO.getUserID());
        theTag.setTagName(tagDTO.getTagName());
        theTag.setTagID(tagDTO.getTagID());
        return theTag;
    }

    @Override
    public Tag findTagByName(String tag, User user){
        String sqlQuery = "SELECT tag_id, tag, user_id from tags WHERE tag = ? AND user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, tag, user.getId());
        List<Tag> tags = new ArrayList<>();
        while(results.next()){
            Tag theTag = mapRowToTag(results);
            tags.add(theTag);
        }
        return tags.get(0);
    }

    public TagIndexDTO mapRowToTagIndexDTO(SqlRowSet rowSet){
        TagIndexDTO theTagIndexDTO = new TagIndexDTO();
        theTagIndexDTO.setCount(rowSet.getInt("count"));
        theTagIndexDTO.setTagID(rowSet.getLong("tag_id"));
        theTagIndexDTO.setTagName(rowSet.getString("tag"));
        theTagIndexDTO.setUserID(rowSet.getLong("user_id"));
        return theTagIndexDTO;
    }

}
