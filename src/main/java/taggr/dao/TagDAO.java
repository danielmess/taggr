package taggr.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import taggr.models.Tag;
import taggr.models.TagDTO;
import taggr.models.TagIndexDTO;
import taggr.models.User;

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
