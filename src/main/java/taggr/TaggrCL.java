package taggr;

import java.util.Scanner;

public class TaggrCL {
    public TaggrCL() {
    }

    public static void main(String[] args) {
        System.out.println("Hello! Welcome to the command-line version of Taggr!");
        boolean finishedTopMenuLoop = false;

        while(true) {
            while(!finishedTopMenuLoop) {
                System.out.println("What would you like to do? Your options are:\n1) Add/Delete Photos\n2) Edit Photos (NOT IMPLEMENTED) \n3) List Photos (NOT IMPLEMENTED) \n4) Exit Taggr\n Please select option number and press enter.");
                Scanner input = new Scanner(System.in);
                int startingChoice = Integer.parseInt(input.nextLine());
                if (startingChoice == 1) {
                    boolean finishedMenuChoice1Loop = false;

                    while(!finishedMenuChoice1Loop) {
                        System.out.println("What would you like to do? \n1) Add Photo \n2) Delete Photo (NOT IMPLEMENTED) \n3) Go back \nPlease select option number and press enter.");
                        int addDeleteMenuChoice = Integer.parseInt(input.nextLine());
                        if (addDeleteMenuChoice == 1) {
                            System.out.println("Please enter what you'd like to do: \n1) Stay in Add/Delete Photos \n2) exit to main menu. Please select option number and hit enter.");
                            int addMenuChoiceInt = Integer.parseInt(input.nextLine());
                            if (addMenuChoiceInt == 2) {
                                finishedMenuChoice1Loop = true;
                            }
                        } else if (addDeleteMenuChoice == 2) {
                            System.out.println("Delete is not implemented yet.");
                        } else if (addDeleteMenuChoice == 3) {
                            finishedMenuChoice1Loop = true;
                        } else {
                            System.out.println("I'm sorry, other features are still being implemented");
                            finishedMenuChoice1Loop = true;
                        }
                    }
                } else if (startingChoice == 4) {
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
