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
        //set default user
        User testUser = new User("Tester T. Testerington");
        User commandLineUser = testUser;

        while(true) {
            while(!finishedTopMenuLoop) {
                System.out.println("What would you like to do? Your options are: \n1) User Options\n2) Add/Delete Photos\n3) Edit Photos (NOT IMPLEMENTED) \n4) List Photos (NOT IMPLEMENTED) \n5) Exit Taggr\n Please select option number and press enter.");
                Scanner input = new Scanner(System.in);
                int startingChoice = Integer.parseInt(input.nextLine());
                if (startingChoice ==1) {
                    boolean finishedUserMenuChoiceLoop = false;
                    while(!finishedUserMenuChoiceLoop) {
                        System.out.println("What would you like to do? \n" +
                                "1) Select User (NOT IMPLEMENTED) \n2) Add User \n3)Delete User and Photos (NOT IMPLEMENTED)");
                        int userMenuChoice = Integer.parseInt(input.nextLine());
                        if (userMenuChoice == 1) {
                            //System.out.println("Please enter username and press enter.");
                            System.out.println("I'm sorry, that option isn't implemented yet.");


                        }
                    }
                }
                else if (startingChoice == 2) {
                    boolean finishedAddMenuChoiceLoop = false;

                    while(!finishedAddMenuChoiceLoop) {
                        System.out.println("What would you like to do? \n1) Add Photo \n2) Delete Photo \n3) Go back \nPlease select option number and press enter.");
                        int addDeleteMenuChoice = Integer.parseInt(input.nextLine());
                        if (addDeleteMenuChoice == 1) {
                            commandLineUser.addPhotoFromCommandLine();
                            System.out.println("Please enter what you'd like to do: \n1) Stay in Add/Delete Photos \n2) exit to main menu. Please select option number and hit enter.");
                            int addMenuChoiceInt = Integer.parseInt(input.nextLine());
                            if (addMenuChoiceInt == 2) {
                                finishedAddMenuChoiceLoop = true;
                            }
                        } else if (addDeleteMenuChoice == 2) {
                            commandLineUser.deletePhotoFromCommandLine();
                            System.out.println("Please enter what you'd like to do: \n1) Stay in Add/Delete Photos \n2) exit to main menu. Please select option number and hit enter.");
                            int addMenuChoiceInt = Integer.parseInt(input.nextLine());
                            if (addMenuChoiceInt == 2) {
                                finishedAddMenuChoiceLoop = true;
                            }

                        } else if (addDeleteMenuChoice == 3) {
                            finishedAddMenuChoiceLoop = true;
                        } else {
                            System.out.println("I'm sorry, you need to pick one of the numbers listed above.");
                            finishedAddMenuChoiceLoop = true;
                        }
                    }
                } else if (startingChoice == 5) {
                    finishedTopMenuLoop = true;
                } else {
                    System.out.println("I'm sorry, only Add/Delete and Exit options are currently working. Please select again!");
                }
            }

            System.out.println("Thank you for using the Taggr prototype. Have a great day, and keep snapping!");
            return;
        }
    }
}
