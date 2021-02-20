package taggr;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCuserDAO implements UserDAO{

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
    public void displayUserTagsIndexSQL() {

    }

    @Override
    //mapped  in SQL file
    public void displayUserTagsSQL() {

    }

    @Override
    //mapped in SQL file
    public void displayUserPhotoSQL() {

    }

    @Override
    public void listUserPhotosBySearchTagSQL(String tag) {

    }

    @Override
    public void listUserPhotosBySearchDescSQL(String keyword) {

    }
}
