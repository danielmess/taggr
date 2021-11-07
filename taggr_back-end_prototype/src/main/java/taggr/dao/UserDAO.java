package taggr.dao;

import taggr.models.Photo;
import taggr.models.Tag;
import taggr.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserDAO {


    public User saveUser(String userName, String password, String first_name, String last_name, String email);

    public boolean isUsernameAndPasswordValid(String userName, String password);

    public Map<Tag, Integer> getUserTagsIndexSQL (int user_id);

    public Set<Tag> getUserTagsSQL(int user_id);

    public void displayUserPhotoSQL();


}
