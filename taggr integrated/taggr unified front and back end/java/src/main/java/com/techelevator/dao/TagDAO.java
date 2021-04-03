package com.techelevator.dao;

import com.techelevator.model.Tag;
import com.techelevator.model.TagDTO;
import com.techelevator.model.TagIndexDTO;
import com.techelevator.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Set;

public interface TagDAO {
    public Set<Tag> findUserTags(long userID);

    public Set<Tag> createTagsSetFromCSV(String tagscsv, User user);

    public TagDTO mapRowToTagDTO(SqlRowSet rowSet);

    public Tag mapRowToTag(SqlRowSet rowSet);

    public Tag convertTagDTOtoTag(TagDTO tagDTO);

    public Set<TagDTO> findUserTagDTOs(long userID);

    public Tag findTagByName(String tag, User user);

    public List<TagIndexDTO> getUserTagIndex(User user);
}
