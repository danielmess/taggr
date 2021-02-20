package taggr;

import java.util.Set;

public interface UserDAO {


    public User saveUser(String userName, String password, String first_name, String last_name, String email);


    public boolean isUsernameAndPasswordValid(String userName, String password);



    public void displayUserTagsIndexSQL ();

    public void displayUserTagsSQL();

    public void displayUserPhotoSQL();

    public void listUserPhotosBySearchTagSQL(String tag);

    public void listUserPhotosBySearchDescSQL(String keyword);


}
