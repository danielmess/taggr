package taggr;
import java.util.Scanner;

public class TaggrScratch {
    public static void main(String[] args) {
        boolean destroyerMenuFinished = false;
        Scanner input = new Scanner(System.in);
        while (!destroyerMenuFinished) {

            System.out.println("this is a test");
            System.out.println("Choose the form of the destroyer:");
            System.out.println("1) a volcano but HUGE");
            System.out.println("2) The Stay-Puff Marshmallow Man");
            System.out.println("3) A stack overflow but, like, for reality, man.");
            System.out.println("4) Run Away and Exit!!!");
            System.out.println("Please enter the number of your destroyer choice and hit enter:");
            int destroyerChoice = Integer.parseInt(input.nextLine());
            switch (destroyerChoice) {
                case 1:
                    System.out.println("\nKABOOOOOM\n");
                    break;
                case 2:
                    System.out.println("\nThreatening marshmallow noises!\n ");
                    marshmallowOptions();
                    break;
                case 3:
                    System.out.println("\n0111011011 01 01 0101010101@@@@!!!\n ");
                    break;
                case 4:
                    System.out.println("\nFine, be that way.\n");
                    destroyerMenuFinished = true;
                default:
                    System.out.println("\n*annoyed voice* you have to choose a VALID form of the destroyer\n");
            }
        }
        System.out.println("buh-bye");
    }

    public static void marshmallowOptions(){
        Scanner input = new Scanner(System.in);
        boolean marshmallowMenuFinished = false;
        while(!marshmallowMenuFinished){
            System.out.println("What will you do???");
            System.out.println("1) panic");
            System.out.println("2) cross the streams");
            System.out.println("3) I would like to be excluded from this narrative and leave this menu.");
            int marshmallowChoice = Integer.parseInt(input.nextLine());
            switch (marshmallowChoice) {
                case 1:
                    System.out.println("That's not very helpful. Now you'll have to choose something else.");
                    break;
                case 2:
                    System.out.println("I thought we were NOT supposed to cross the streams");
                    System.out.println("but here goes");
                    System.out.println("*marshmallow fluff explosion*");
                    marshmallowMenuFinished = true;
                    break;
                case 3:
                    System.out.println("boring, bye");
                    marshmallowMenuFinished = true;
                    break;
                default:
                    System.out.println("you ARE indecisive, aren't you");
            }
        }
    }
}
