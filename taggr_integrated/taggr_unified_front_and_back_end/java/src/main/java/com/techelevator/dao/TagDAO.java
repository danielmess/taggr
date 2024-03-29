package com.techelevator.dao;

import com.techelevator.model.Tag;
import com.techelevator.model.TagDTO;
import com.techelevator.model.TagIndexDTO;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Set;

public interface TagDAO {
    public List<Tag> findUserTags(long userID);

    public List<Tag> createTagsSetFromCSV(String tagsCsv, User user);

    public TagDTO mapRowToTagDTO(SqlRowSet rowSet);

    public Tag mapRowToTag(SqlRowSet rowSet);

    public Tag convertTagDTOtoTag(TagDTO tagDTO);

    public List<TagDTO> findUserTagDTOs(long userID);

    public Tag findTagByName(String tag, User user);

    public Tag findTagById(long tagId, User user);

    public List<TagIndexDTO> getUserTagIndex(User user);
}
