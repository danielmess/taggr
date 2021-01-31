package taggr;

import java.util.Scanner;

public class TaggrCL {
    public TaggrCL() {
    }

    public static void main(String[] args) {
        //welcome message
        System.out.println("Hello! Welcome to the command-line version of Taggr!");
        //set flag for top-line menu while-loop
        boolean finishedTopMenuLoop = false;
        //set default user - this will be changed later when I can implement User Options.
        User testUser = new User("Tester T. Testerington");
        User commandLineUser = testUser;
        while (!finishedTopMenuLoop) {
            System.out.println("What would you like to do? Your options are:");
            System.out.println("1) User Options - UNIMPLEMENTED");
            System.out.println("2) Add/Delete Photos");
            System.out.println("3) Edit Photo Information");
            System.out.println("4) List Photos By Tags Or Description Keywords");
            System.out.println("5) Exit Taggr");
            System.out.println("  Please select option number and press enter.");
            Scanner input = new Scanner(System.in);
            int topMenuChoice = Integer.parseInt(input.nextLine());
            switch (topMenuChoice) {
                case 1:
                    System.out.println("I'm sorry, but User Options are not implemented yet.");
                    break;
                case 2:
                    commandLineUser.addDeleteMenu(); // run static method for add/delete menu
                    break;
                case 3:
                    System.out.println("Edit Photo Information menu:");
                    //editPhotosMenu(); - run method for edit Photos menu
                    break;
                case 4:
                    commandLineUser.listPhotosMenu(); //- run method for list Photos menu
                    break;
                case 5:
                    finishedTopMenuLoop = true;
                    break;
                default:
                    System.out.println("Please choose a supported menu option.");
            }
        }
        System.out.println("Thank you for using the Taggr prototype. Have a great day, and keep snapping!");
    }


//    public void addDeleteMenu() {
//        //set flag for add-delete menu while loop
//        boolean finishedAddDeleteMenuLoop = false;
//        //begin while loop
//        while (!finishedAddDeleteMenuLoop) {
//            System.out.println("Add/Delete Photos menu:");
//            System.out.println("1) Add a Photo to current User's photoset");
//            System.out.println("2) Delete a Photo from current User's photoset");
//            System.out.println("3) Exit to Main Menu");
//            System.out.println("Please select option number and press enter.");
//            Scanner input = new Scanner(System.in);
//            int addDeleteMenuChoice = Integer.parseInt(input.nextLine());
//            switch (addDeleteMenuChoice) {
//                case 1:
//                    addPhotoFromCommandLine();
//                    //addphoto method
//                    break;
//                case 2:
//                    //delete photo method;
//                    deletePhotoFromCommandLine();
//                    break;
//                case 3:
//                    System.out.println("Now exiting to main menu.");
//                    finishedAddDeleteMenuLoop = true;
//                    break;
//                default:
//                    System.out.println("Please choice one of the supported menu options.");
//            }
//        }
//    }
}
//        while(true) {
//            while(!finishedTopMenuLoop) {
//                System.out.println("What would you like to do? Your options are: \n1) User Options\n2) Add/Delete Photos\n3) Edit Photos (NOT IMPLEMENTED) \n4) List Photos (NOT IMPLEMENTED) \n5) Exit Taggr\n Please select option number and press enter.");
//                Scanner input = new Scanner(System.in);
//                int startingChoice = Integer.parseInt(input.nextLine());
//                if (startingChoice ==1) {
//                    boolean finishedUserMenuChoiceLoop = false;
//                    while(!finishedUserMenuChoiceLoop) {
//                        System.out.println("What would you like to do? \n" +
//                                "1) Select User (NOT IMPLEMENTED) \n2) Add User \n3)Delete User and Photos (NOT IMPLEMENTED)");
//                        int userMenuChoice = Integer.parseInt(input.nextLine());
//                        if (userMenuChoice == 1) {
//                            //System.out.println("Please enter username and press enter.");
//                            System.out.println("I'm sorry, that option isn't implemented yet.");
//
//
//                        }
//                    }
//                }
//                else if (startingChoice == 2) {
//                    boolean finishedAddMenuChoiceLoop = false;
//
//                    while(!finishedAddMenuChoiceLoop) {
//                        System.out.println("What would you like to do? \n1) Add Photo \n2) Delete Photo \n3) Go back \nPlease select option number and press enter.");
//                        int addDeleteMenuChoice = Integer.parseInt(input.nextLine());
//                        if (addDeleteMenuChoice == 1) {
//                            commandLineUser.addPhotoFromCommandLine();
//                            System.out.println("Please enter what you'd like to do: \n1) Stay in Add/Delete Photos \n2) exit to main menu. Please select option number and hit enter.");
//                            int addMenuChoiceInt = Integer.parseInt(input.nextLine());
//                            if (addMenuChoiceInt == 2) {
//                                finishedAddMenuChoiceLoop = true;
//                            }
//                        } else if (addDeleteMenuChoice == 2) {
//                            commandLineUser.deletePhotoFromCommandLine();
//                            System.out.println("Please enter what you'd like to do: \n1) Stay in Add/Delete Photos \n2) exit to main menu. Please select option number and hit enter.");
//                            int addMenuChoiceInt = Integer.parseInt(input.nextLine());
//                            if (addMenuChoiceInt == 2) {
//                                finishedAddMenuChoiceLoop = true;
//                            }
//
//                        } else if (addDeleteMenuChoice == 3) {
//                            finishedAddMenuChoiceLoop = true;
//                        } else {
//                            System.out.println("I'm sorry, you need to pick one of the numbers listed above.");
//                            finishedAddMenuChoiceLoop = true;
//                        }
//                    }
//                } else if (startingChoice == 5) {
//                    finishedTopMenuLoop = true;
//                } else {
////                    System.out.println("I'm sorry, only Add/Delete and Exit options are currently working. Please select again!");
////                }
////            }
//
//            System.out.println("Thank you for using the Taggr prototype. Have a great day, and keep snapping!");
//
//        }
//    }
//}
