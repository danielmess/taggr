package taggr;

import java.util.Set;

public interface PhotoDAO {

    public void createNewPhotoAndAddToUserSQL(String photoURL, String photoDescription, Set<String> tagsSet);

    public void deletePhotoFromUserSQL (Photo photoToDelete);

    public void givePhotoNewDescriptionSQL (Photo photoToEdit, String newDescription);

    public Photo retrieveUserPhotoFromURLSQL (String url);

    public void addTagToPhotoSQL (Photo photoToEdit, String tag);

    public void deleteTagFromPhotoSQL (Photo photoToEdit, String tag);
}
