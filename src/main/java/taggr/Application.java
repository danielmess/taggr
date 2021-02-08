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

        boolean mainMenuLoopFinished = false;
        while (!mainMenuLoopFinished) {
            String mainSelection = ui.promptForSelection(MAIN_MENU_OPTIONS);
            switch (mainSelection) {
                case MAIN_MENU_OPTION_USER_MENU:
                    System.out.println("User options are not yet implemented");
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
                    String url = ui.promptForString("Please enter the URL of the photo to add");
                    String photoDescription = ui.promptForString("Please enter the description of the photo to add.");
                    String tagsString = ui.promptForString("Please enter all tags you'd like the photo to have, separated by a comma and a space. Hit enter when finished.");
                    testUser.addPhotoToUser(url, photoDescription, tagsString);
                    break;
                case ADD_DELETE_MENU_OPTION_DELETE_PHOTO:
                    String urlToDelete = ui.promptForString("Please enter the URL of the photo to delete");
                    testUser.deletePhotoFromUser(urlToDelete);
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
                    String url = ui.promptForString("Please enter the photo URL and hit enter.");
                    String newDescription = ui.promptForString("Please enter the new description and hit enter.");
                    testUser.rewritePhotoDescription(url, newDescription);
                    break;
                case EDIT_PHOTO_MENU_OPTION_ADD_TAG:
                    //RUN DELETE PHOTO METHOD ON USER
                    String urlAddTag = ui.promptForString("Please enter the photo URL and hit enter.");
                    String newTag = ui.promptForString("Please enter the tag to add and hit enter.");
                    testUser.addTagToPhoto(urlAddTag, newTag);
                    break;
                case EDIT_PHOTO_MENU_OPTION_DELETE_TAG:
                    //RUN DELETE PHOTO METHOD ON USER
                    String urlDelTag = ui.promptForString("Please enter the photo URL and hit enter.");
                    String deleteTag = ui.promptForString("Please enter the tag to add and hit enter.");
                    testUser.deleteTagFromPhoto(urlDelTag, deleteTag);
                    break;
                case EDIT_PHOTO_MENU_OPTION_INDEX_USER_TAGS:
                    //run method
                    testUser.printUserTagsIndex();
                    break;
                case EDIT_PHOTO_MENU_OPTION_LIST_USER_TAGS:
                    //RUN METHOD
                    ui.output(testUser.getUserTagsAsString());
                    break;
                case EDIT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS:
                    //RUN METHOD
                    testUser.printUserPhotosInfo();
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
                    ui.output("taggr will run a keyword search and bring back all photos with tags that apply.");
                    String tagToSearch = ui.promptForString("Please input the keyword you want to search for in your photoset's tags and hit enter.");
                    testUser.printUserPhotosTagSearch(tagToSearch);
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_BY_KEYWORD:
                    //RUN DELETE PHOTO METHOD ON USER
                    ui.output("taggr will run a keyword search and bring back all photos with descriptions that apply.");
                    String descriptionKeyWord = ui.promptForString("Please input the keyword you want to search for in the photoset's descriptions and hit enter.");
                    testUser.printUserPhotosDescriptionSearch(descriptionKeyWord);
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_INDEX_USER_TAGS:
                    //RUN DELETE PHOTO METHOD ON USER
                    testUser.printUserTagsIndex();
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_TAGS:
                    //run method
                    ui.output(testUser.getUserTagsAsString());
                    break;
                case LIST_PRINT_PHOTO_MENU_OPTION_LIST_USER_PHOTOS:
                    //RUN METHOD
                    testUser.printUserPhotosInfo();
                    break;
                case LIST_PRINT_PHOTO_MENU_EXIT:
                    listPrintPhotoSubMenuLoopFinished = true;
                    break;
            }
        }
    }




}
