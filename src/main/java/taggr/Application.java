package taggr;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import taggr.dao.PhotoDAO;
import taggr.dao.TagDAO;
import taggr.dao.UserDAO;
import taggr.models.Photo;
import taggr.models.Tag;
import taggr.models.TagIndexDTO;
import taggr.models.User;
import taggr.dao.jdbc.JDBCuserDAO;
import taggr.dao.jdbc.JDBCphotoDAO;
import taggr.dao.jdbc.JDBCtagDAO;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;


public class Application {
    //list main menu option strings
    private static final String MAIN_MENU_OPTION_USER_MENU = "User Options - Unimplemented";
    private static final String MAIN_MENU_OPTION_ADD_DELETE_MENU = "Add/Delete Photos";
    private static final String MAIN_MENU_OPTION_EDIT_PHOTO_INFO_MENU = "Edit Photo Information";
    private static final String MAIN_MENU_OPTION_LIST_SEARCH_MENU = "List/Search Photos or Tags";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit taggr";

    //future strings for user menu can be listed here.
    private static final String USER_MENU_OPTION_LOGIN = "Login with your user id and password";
    private static final String USER_MENU_OPTION_CREATE_USER = "Create a new user and password";
    private static final String USER_MENU_OPTION_LOGOUT = "Logout of the current user profile";
    private static final String USER_MENU_OPTION_EXIT = "Exit to Main Menu";

    //list add/delete photo submenu option strings
    private static final String ADD_DELETE_MENU_OPTION_ADD_PHOTO = "Add a Photo to current User's Photoset";
    private static final String ADD_DELETE_MENU_OPTION_DELETE_PHOTO = "Delete a Photo from current User's Photoset";
    private static final String ADD_DELETE_MENU_EXIT = "Exit to Main Menu";

    //list edit photo submenu option strings
    private static final String EDIT_PHOTO_MENU_OPTION_NEW_DESCRIPTION = "Give a Photo a new description";
    private static final String EDIT_PHOTO_MENU_OPTION_ADD_TAG = "Add a tag to a Photo";
    private static final String EDIT_PHOTO_MENU_OPTION_DELETE_TAG = "Delete a tag from a Photo.";
    private static final String EDIT_PHOTO_MENU_OPTION_INDEX_USER_TAGS = "List all tags for User with their number of occurrences in user's Photoset";
    private static final String EDIT_PHOTO_MENU_OPTION_LIST_USER_TAGS = "List all tags for User.";
    private static final String EDIT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS = "List all User's photos";
    private static final String EDIT_PHOTO_MENU_OPTION_EXIT = "Exit to Main Menu";


    //list list/print photo submenu option strings
    private static final String LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_TAG = "Search and list all photos by tag";
    private static final String LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_KEYWORD = "Search and list all photos by description keyword";
    private static final String LIST_PRINT_PHOTO_MENU_OPTION_LIST_INDEX_USER_TAGS = "List all tags for User with their number of occurrences in user's Photoset";
    private static final String LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_TAGS = "List all tags for User.";
    private static final String LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS = "List all User's photos";
    private static final String LIST_PRINT_PHOTO_MENU_EXIT = "Exit to Main Menu";


    //String arrays for main menu options and submenu options
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_USER_MENU, MAIN_MENU_OPTION_ADD_DELETE_MENU,
            MAIN_MENU_OPTION_EDIT_PHOTO_INFO_MENU, MAIN_MENU_OPTION_LIST_SEARCH_MENU, MAIN_MENU_OPTION_EXIT};

    //User menu options String[] will go here in future implementation.

    private static final String[] USER_MENU_OPTIONS = {USER_MENU_OPTION_LOGIN, USER_MENU_OPTION_CREATE_USER, USER_MENU_OPTION_LOGOUT, USER_MENU_OPTION_EXIT};

    private static final String[] ADD_DELETE_MENU_OPTIONS = {ADD_DELETE_MENU_OPTION_ADD_PHOTO, ADD_DELETE_MENU_OPTION_DELETE_PHOTO, ADD_DELETE_MENU_EXIT};

    private static final String[] EDIT_PHOTO_MENU_OPTIONS = {EDIT_PHOTO_MENU_OPTION_NEW_DESCRIPTION, EDIT_PHOTO_MENU_OPTION_ADD_TAG,
            EDIT_PHOTO_MENU_OPTION_DELETE_TAG, EDIT_PHOTO_MENU_OPTION_INDEX_USER_TAGS, EDIT_PHOTO_MENU_OPTION_LIST_USER_TAGS,
            EDIT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS, EDIT_PHOTO_MENU_OPTION_EXIT};

    private static final String[] LIST_PRINT_PHOTO_MENU_OPTIONS = {LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_TAG,
            LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_KEYWORD, LIST_PRINT_PHOTO_MENU_OPTION_LIST_INDEX_USER_TAGS,
            LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_TAGS, LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS, LIST_PRINT_PHOTO_MENU_EXIT};

    private final MenuAndCLI ui = new MenuAndCLI(System.in, System.out);
    private User testUser = new User();


    public static void main(String[] args) {
        /* This datasource will be used for creating connections to the database.
         * Below, we provide the information required to make database connections */


        Application application = new Application();
        application.run();
    }

    public void run() {
        //prework before menu
        System.out.println("Hello! Welcome to the command-line version of Taggr!");
        BasicDataSource taggrDataSource = new BasicDataSource();


        taggrDataSource.setUrl("jdbc:postgresql://localhost:5432/taggr");
        taggrDataSource.setUsername("postgres");
        taggrDataSource.setPassword("postgres1");

        /* The JdbcTemplate is the main interface we use to interact with databases using
         * Spring JDBC. */
        JdbcTemplate taggrJDBCTemplate = new JdbcTemplate(taggrDataSource);



        UserDAO userDA0 = new JDBCuserDAO(taggrDataSource);
        TagDAO tagDAO = new JDBCtagDAO(taggrDataSource);
        PhotoDAO photoDAO = new JDBCphotoDAO(taggrDataSource, tagDAO);


        boolean mainMenuLoopFinished = false;
        while (!mainMenuLoopFinished) {
            String mainSelection = ui.promptForSelection(MAIN_MENU_OPTIONS);
            switch (mainSelection) {
                case MAIN_MENU_OPTION_USER_MENU:
                    //ui.output("User options are not yet implemented");
                    userSubMenu(); //- commented out until User Menu methods are implemented - allowing for demo
                    break;
                case MAIN_MENU_OPTION_ADD_DELETE_MENU:
                    //RUN ADD/DELETE METHOD, DEFINED BELOW
                    addDeleteSubMenu();
                    break;
                case MAIN_MENU_OPTION_EDIT_PHOTO_INFO_MENU:
                    //RUN EDIT PHOTO MENU, DEFINED BELOW
                    editPhotoSubMenu();
                    break;
                case MAIN_MENU_OPTION_LIST_SEARCH_MENU:
                    //RUN LIST/SEARCH MENU, DEFINED BELOW
                    listPrintPhotoSubMenu();
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    ui.output("Thank you for using taggr. Have a great day and keep snapping!");
                    mainMenuLoopFinished = true;
                    break;
            }
        }
    }

    public void userSubMenu(){
        boolean userSubMenuFinished = false;
        while (!userSubMenuFinished) {
            String userMenuSelection = ui.promptForSelection(USER_MENU_OPTIONS);
            switch (userMenuSelection) {
                case USER_MENU_OPTION_LOGIN:

//                    String username = ui.promptForString("Please enter the username to log in as. This is terrible security, I know.  ");
                    long newUserID = ui.promptForLong("Please enter the userID to log in as. This is terrible security but do it anyways!  ");
                    testUser.setUser_id(newUserID);
//                    testUser.setUserName(username);
                    break;
                case USER_MENU_OPTION_CREATE_USER:
                    //add user creation method here
                    break;
                case USER_MENU_OPTION_LOGOUT:
                    //add user logout method here
                case USER_MENU_OPTION_EXIT:
                    userSubMenuFinished = true;
                    break;
            }
        }
    }

    public void addDeleteSubMenu(){
        BasicDataSource taggrDataSource = new BasicDataSource();


        taggrDataSource.setUrl("jdbc:postgresql://localhost:5432/taggr");
        taggrDataSource.setUsername("postgres");
        taggrDataSource.setPassword("postgres1");

        /* The JdbcTemplate is the main interface we use to interact with databases using
         * Spring JDBC. */
        JdbcTemplate taggrJDBCTemplate = new JdbcTemplate(taggrDataSource);



        UserDAO userDA0 = new JDBCuserDAO(taggrDataSource);
        TagDAO tagDAO = new JDBCtagDAO(taggrDataSource);
        PhotoDAO photoDAO = new JDBCphotoDAO(taggrDataSource, tagDAO);
        boolean addDeleteSubMenuLoopFinished = false;
        while (!addDeleteSubMenuLoopFinished) {
            String addDeleteSelection = ui.promptForSelection(ADD_DELETE_MENU_OPTIONS);
            switch (addDeleteSelection) {
                case ADD_DELETE_MENU_OPTION_ADD_PHOTO:
                    String url = ui.promptForString("Please enter the URL of the photo to add:  ");
                    String photoDescription = ui.promptForString("Please enter the description of the photo to add:  ");
                    String tagsString = ui.promptForString("Please enter all tags you'd like the photo to have, separated by a comma and a space. Hit enter when finished.  ");
                    Set<Tag> photoTagsSet = tagDAO.createTagsSetFromCSV(tagsString, testUser);
//                    testUser.addPhotoToUser(url, photoDescription, tagsString);
                    photoDAO.createNewPhotoAndAddToUserSQL(url, photoDescription, photoTagsSet, testUser);
                    break;
                case ADD_DELETE_MENU_OPTION_DELETE_PHOTO:
                    String urlToDelete = ui.promptForString("Please enter the URL of the photo to delete:   ");
                    //testUser.deletePhotoFromUser(urlToDelete);
                    photoDAO.deletePhotoFromUserSQL(urlToDelete, testUser);
                    break;
                case ADD_DELETE_MENU_EXIT:
                    addDeleteSubMenuLoopFinished = true;
                    break;
            }
        }
    }

    public void editPhotoSubMenu() {
        BasicDataSource taggrDataSource = new BasicDataSource();


        taggrDataSource.setUrl("jdbc:postgresql://localhost:5432/taggr");
        taggrDataSource.setUsername("postgres");
        taggrDataSource.setPassword("postgres1");

        /* The JdbcTemplate is the main interface we use to interact with databases using
         * Spring JDBC. */
        JdbcTemplate taggrJDBCTemplate = new JdbcTemplate(taggrDataSource);



        UserDAO userDA0 = new JDBCuserDAO(taggrDataSource);
        TagDAO tagDAO = new JDBCtagDAO(taggrDataSource);
        PhotoDAO photoDAO = new JDBCphotoDAO(taggrDataSource, tagDAO);
        boolean editPhotoSubMenuLoopFinished = false;
        while (!editPhotoSubMenuLoopFinished) {
            String editSelection = ui.promptForSelection(EDIT_PHOTO_MENU_OPTIONS);
            switch (editSelection) {
                case EDIT_PHOTO_MENU_OPTION_NEW_DESCRIPTION:
                    String url = ui.promptForString("Please enter the photo URL and hit enter.  ");
                    String newDescription = ui.promptForString("Please enter the new description and hit enter.  ");
                    //testUser.rewritePhotoDescription(url, newDescription);
                    photoDAO.givePhotoNewDescriptionSQL(url, newDescription, testUser);
                    break;
                case EDIT_PHOTO_MENU_OPTION_ADD_TAG:
                    String urlAddTag = ui.promptForString("Please enter the photo URL and hit enter.  ");
                    String newTagString = ui.promptForString("Please enter the tag to add and hit enter.  ");
                    photoDAO.addTagToPhotoSQL(urlAddTag, newTagString, testUser);
                    break;
                case EDIT_PHOTO_MENU_OPTION_DELETE_TAG:
                    String urlDelTag = ui.promptForString("Please enter the photo URL and hit enter.  ");
                    String deleteTag = ui.promptForString("Please enter the tag to delete and hit enter.  ");
                    photoDAO.deleteTagFromPhotoSQL(urlDelTag, deleteTag, testUser);
                    break;
                case EDIT_PHOTO_MENU_OPTION_INDEX_USER_TAGS:
                    List<TagIndexDTO> tagsIndex = tagDAO.getUserTagIndex(testUser);
                    ui.printUserTagsIndexInfo(tagsIndex);
                    break;
                case EDIT_PHOTO_MENU_OPTION_LIST_USER_TAGS:
                    ui.output(testUser.getUserTagsAsString());
                    break;
                case EDIT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS:
                    List<Photo> userPhotos = photoDAO.listAllUserPhotosFromSQL(testUser);
                    ui.printPhotoListInfo(userPhotos);
                    break;
                case EDIT_PHOTO_MENU_OPTION_EXIT:
                    editPhotoSubMenuLoopFinished = true;
                    break;
            }
        }
    }

    public void listPrintPhotoSubMenu() {
        BasicDataSource taggrDataSource = new BasicDataSource();


        taggrDataSource.setUrl("jdbc:postgresql://localhost:5432/taggr");
        taggrDataSource.setUsername("postgres");
        taggrDataSource.setPassword("postgres1");

        /* The JdbcTemplate is the main interface we use to interact with databases using
         * Spring JDBC. */
        JdbcTemplate taggrJDBCTemplate = new JdbcTemplate(taggrDataSource);



        UserDAO userDA0 = new JDBCuserDAO(taggrDataSource);
        TagDAO tagDAO = new JDBCtagDAO(taggrDataSource);
        PhotoDAO photoDAO = new JDBCphotoDAO(taggrDataSource, tagDAO);
        boolean listPrintPhotoSubMenuLoopFinished = false;
        while (!listPrintPhotoSubMenuLoopFinished) {
            String listPrintSelection = ui.promptForSelection(LIST_PRINT_PHOTO_MENU_OPTIONS);
            switch (listPrintSelection) {
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_TAG:

                    ui.output("taggr will run a tag search and bring back all photos that apply.  ");
                    String tagSearchString = ui.promptForString("Please input the keyword you want to search for in your photoSet's tags and hit enter.  ");
//                    Tag tagToSearch = new Tag();
//                    tagToSearch.setTagName(tagSearchString);
//                    testUser.printUserPhotosTagSearch(tagToSearch);
                    List<Photo> photoList = photoDAO.listUserPhotosBySearchTagSQL(tagSearchString, testUser);
                    ui.printPhotoListInfo(photoList);
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_KEYWORD:

                    ui.output("taggr will run a keyword search and bring back all photos with descriptions that apply.  ");
                    String descriptionKeyWord = ui.promptForString("Please input the keyword you want to search for in the photoSet's descriptions and hit enter.  ");
//                    testUser.printUserPhotosDescriptionSearch(descriptionKeyWord);
                    List<Photo> photoList1 = photoDAO.listUserPhotosBySearchDescSQL(descriptionKeyWord, testUser);
                    ui.printPhotoListInfo(photoList1);
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_INDEX_USER_TAGS:
                    List<TagIndexDTO> tagsIndex = tagDAO.getUserTagIndex(testUser);
                    ui.printUserTagsIndexInfo(tagsIndex);
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_TAGS:
                    ui.output(testUser.getUserTagsAsString());
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS:
                    List<Photo> userPhotos = photoDAO.listAllUserPhotosFromSQL(testUser);
                    ui.printPhotoListInfo(userPhotos);
                    break;
                case LIST_PRINT_PHOTO_MENU_EXIT:
                    listPrintPhotoSubMenuLoopFinished = true;
                    break;
            }
        }
    }




}
