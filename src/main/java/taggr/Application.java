package taggr;

public class Application {
    //list main menu option strings
    private static final String MAIN_MENU_OPTION_USER_MENU = "User Options - Unimplemented";
    private static final String MAIN_MENU_OPTION_ADD_DELETE_MENU = "Add/Delete Photos";
    private static final String MAIN_MENU_OPTION_EDIT_PHOTO_INFO_MENU = "Edit Photo Information";
    private static final String MAIN_MENU_OPTION_LIST_SEARCH_MENU = "List/Search Photos or Tags";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit taggr";

    //future strings for user menu can be implemented below

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

    private static final String[] ADD_DELETE_MENU_OPTIONS = {ADD_DELETE_MENU_OPTION_ADD_PHOTO, ADD_DELETE_MENU_OPTION_DELETE_PHOTO, ADD_DELETE_MENU_EXIT};

    private static final String[] EDIT_PHOTO_MENU_OPTIONS = {EDIT_PHOTO_MENU_OPTION_NEW_DESCRIPTION, EDIT_PHOTO_MENU_OPTION_ADD_TAG,
            EDIT_PHOTO_MENU_OPTION_DELETE_TAG, EDIT_PHOTO_MENU_OPTION_INDEX_USER_TAGS, EDIT_PHOTO_MENU_OPTION_LIST_USER_TAGS,
            EDIT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS, EDIT_PHOTO_MENU_OPTION_EXIT};

    private static final String[] LIST_PRINT_PHOTO_MENU_OPTIONS = {LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_TAG,
            LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_KEYWORD, LIST_PRINT_PHOTO_MENU_OPTION_LIST_INDEX_USER_TAGS,
            LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_TAGS, LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS, LIST_PRINT_PHOTO_MENU_EXIT};

    private final MenuAndCLI ui = new MenuAndCLI(System.in, System.out);
    private   User testUser = new User("Tester T. Testerington");

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        //prework before menu
        System.out.println("Hello! Welcome to the command-line version of Taggr!");
        User commandLineUser = testUser;

        boolean mainMenuLoopFinished = false;
        while (!mainMenuLoopFinished) {
            String mainSelection = ui.promptForSelection(MAIN_MENU_OPTIONS);
            switch (mainSelection) {
                case MAIN_MENU_OPTION_USER_MENU:
                    System.out.println("User options are not yet implemented");
                    break;
                case MAIN_MENU_OPTION_ADD_DELETE_MENU:
                    //RUN ADD/DELETE METHOD, DEFINE BELOW
                    addDeleteSubMenu();
                    break;
                case MAIN_MENU_OPTION_EDIT_PHOTO_INFO_MENU:
                    //RUN EDIT Photo method, define below
                    editPhotoSubMenu();
                    break;
                case MAIN_MENU_OPTION_LIST_SEARCH_MENU:
                    //run list search menu method, define below
                    listPrintPhotoSubMenu();
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    System.out.println("Thank you for using taggr. Have a great day and keep snapping!");
                    mainMenuLoopFinished = true;
                    break;
            }
        }
    }

    public void addDeleteSubMenu() {
        boolean addDeleteSubMenuLoopFinished = false;
        while (!addDeleteSubMenuLoopFinished) {
            String addDeleteSelection = ui.promptForSelection(ADD_DELETE_MENU_OPTIONS);
            switch (addDeleteSelection) {
                case ADD_DELETE_MENU_OPTION_ADD_PHOTO:
                    testUser.addPhotoFromCommandLine();
                    break;
                case ADD_DELETE_MENU_OPTION_DELETE_PHOTO:
                    testUser.deletePhotoFromCommandLine();
                    break;
                case ADD_DELETE_MENU_EXIT:
                    addDeleteSubMenuLoopFinished = true;
                    break;
            }
        }
    }

    public void editPhotoSubMenu() {
        boolean editPhotoSubMenuLoopFinished = false;
        while (!editPhotoSubMenuLoopFinished) {
            String editSelection = ui.promptForSelection(EDIT_PHOTO_MENU_OPTIONS);
            switch (editSelection) {
                case EDIT_PHOTO_MENU_OPTION_NEW_DESCRIPTION:
                    //RUN EDIT PHOTO DESCRIPTION METHOD ON USER
                    break;
                case EDIT_PHOTO_MENU_OPTION_ADD_TAG:
                    //RUN DELETE PHOTO METHOD ON USER
                    break;
                case EDIT_PHOTO_MENU_OPTION_DELETE_TAG:
                    //RUN DELETE PHOTO METHOD ON USER
                    break;
                case EDIT_PHOTO_MENU_OPTION_INDEX_USER_TAGS:
                    //run method
                    break;
                case EDIT_PHOTO_MENU_OPTION_LIST_USER_TAGS:
                    //RUN METHOD
                    break;
                case EDIT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS:
                    //RUN METHOD
                    break;
                case EDIT_PHOTO_MENU_OPTION_EXIT:
                    editPhotoSubMenuLoopFinished = true;
                    break;
            }
        }
    }

    public void listPrintPhotoSubMenu() {
        boolean listPrintPhotoSubMenuLoopFinished = false;
        while (!listPrintPhotoSubMenuLoopFinished) {
            String listPrintSelection = ui.promptForSelection(LIST_PRINT_PHOTO_MENU_OPTIONS);
            switch (listPrintSelection) {
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_TAG:
                    //RUN EDIT PHOTO DESCRIPTION METHOD ON USER
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_KEYWORD:
                    //RUN DELETE PHOTO METHOD ON USER
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_INDEX_USER_TAGS:
                    //RUN DELETE PHOTO METHOD ON USER
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_TAGS:
                    //run method
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS:
                    //RUN METHOD
                    break;
                case LIST_PRINT_PHOTO_MENU_EXIT:
                    listPrintPhotoSubMenuLoopFinished = true;
                    break;
            }
        }
    }




}
