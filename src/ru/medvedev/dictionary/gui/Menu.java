package ru.medvedev.dictionary.gui;

import ru.medvedev.dictionary.Dictionary;
import ru.medvedev.dictionary.Locale;
import ru.medvedev.dictionary.command.*;
import java.util.Scanner;

/**
 * Created by Сергей on 27.04.2016.
 */
public class Menu {

    private static final String WELCOME = "Welcome to Dictionary\n-----------------------------------";
    private static final String CHOOSECOM = "Choose command:\n" +
            " 1. Find word\n" +
            " 2. Add word\n" +
            " 3. Update word info \n" +
            " 4. Delete word \n" +
            " 5. Show all words\n" +
            " 6. Exit\n\nEnter: ";
    private static final String ERR = "Incorrect command";
    private static final String CHOOSELOC = "Choose locale:    (RUS, ENG)";

    private static ManagerCommands managerCommands = new ManagerCommands();

    private Menu() {}

    private static void execCommand(Command command) {
        managerCommands.addCommand(command);
        managerCommands.run();
        managerCommands.clearListCommands();
    }

    public static void getMenu() {

        Scanner scanner = new Scanner(System.in);

        Command findWord = new FindWordCommand();
        Command addWord = new AddWordCommand();
        Command updateWord = new UpdateWordInfoCommand();
        Command deleteWord = new DeleteWordCommand();
        Command showAllWords = new ShowAllWordsCommand();

        System.out.println(WELCOME);
        System.out.println(CHOOSELOC);
        Dictionary.setLocale(Locale.valueOf(scanner.next().trim()));

        while(true) {
            System.out.println(CHOOSECOM);

            int ch = scanner.nextInt();

            if (ch == 1) {
                execCommand(findWord);
            } else if (ch == 2) {
                execCommand(addWord);
            } else if (ch == 3) {
                execCommand(updateWord);
            } else if (ch == 4) {
                execCommand(deleteWord);
            } else if (ch == 5) {
                execCommand(showAllWords);
            } else if (ch == 6) {
                break;
            } else {
                System.out.println(ERR);
            }
        }

    }
}
