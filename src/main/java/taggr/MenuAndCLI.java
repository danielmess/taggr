package taggr;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuAndCLI {


    private final Scanner userInput = new Scanner(System.in);

    private PrintWriter out;
    private Scanner in;

    public MenuAndCLI(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println("\n*** " + userInput + " is not a valid option ***\n");
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            //prints out all options that are not secret options; secret options have a blank string as their description.
            String optionDescription = options[i].toString();
            if (!optionDescription.equals("")) {
                out.println(optionNum + ") " + options[i]);
            }
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }

    public void output(String content) {
        System.out.println(); //Print blank line
        System.out.println(content);
    }

    public void pauseOutput() {
        System.out.println("(Press enter to continue)");
        userInput.nextLine();
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return userInput.nextLine();
    }

    public String promptForSelection(String[] options) {
        return (String) getChoiceFromOptions(options);
    }

}
