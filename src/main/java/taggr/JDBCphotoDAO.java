package taggr;

import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCphotoDAO implements PhotoDAO{
    @Override
    public void createNewPhotoAndAddToUserSQL(String photoURL, String photoDescription, Set<String> tagsSet) {

    }

    @Override
    public void deletePhotoFromUserSQL(Photo photoToDelete) {

    }

    @Override
    public void givePhotoNewDescriptionSQL(Photo photoToEdit, String newDescription) {

    }

    @Override
    public Photo retrieveUserPhotoFromURLSQL(String url) {
        return null;
    }

    @Override
    public void addTagToPhotoSQL(Photo photoToEdit, String tag) {

    }

    @Override
    public void deleteTagFromPhotoSQL(Photo photoToEdit, String tag) {

    }
}
