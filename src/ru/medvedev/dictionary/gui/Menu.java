package ru.medvedev.dictionary.gui;

import ru.medvedev.dictionary.Dictionary;
import ru.medvedev.dictionary.command.*;
import ru.medvedev.dictionary.records.GeneralRecord;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Сергей on 27.04.2016.
 */
public class Menu {
    private Menu() {}

    public static void getMenu() {

        Scanner scanner = new Scanner(System.in);

        Command findWord = new FindWordCommand();
        Command addWord = new AddWordCommand();
        Command updateWord = new UpdateWordInfoCommand();
        Command deleteWord = new DeleteWordCommand();
        Command showAllWords = new ShowAllWordsCommand();

        ManagerCommands managerCommands = new ManagerCommands();

        System.out.println("Welcome to Dictionary");
        System.out.println("-----------------------------------");

        while(true) {
            System.out.println("Choose command:\n 1. Find word\n 2. Add word\n 3. Update word info \n 4. Delete word \n 5. Show all words\n 6. Exit");
            System.out.println();
            System.out.println("Enter: ");

            int ch = scanner.nextInt();

            if (ch == 1) {
                managerCommands.addCommand(findWord);
                managerCommands.run();
                managerCommands.clearListCommands();
            } else if (ch == 2) {
                managerCommands.addCommand(addWord);
                managerCommands.run();
                managerCommands.clearListCommands();
            } else if (ch == 3) {
                managerCommands.addCommand(updateWord);
                managerCommands.run();
                managerCommands.clearListCommands();
            } else if (ch == 4) {
                managerCommands.addCommand(deleteWord);
                managerCommands.run();
                managerCommands.clearListCommands();
            } else if (ch == 5) {
                managerCommands.addCommand(showAllWords);
                managerCommands.run();
                managerCommands.clearListCommands();
            } else if (ch == 6) {
                break;
            } else {
                System.out.println("Incorrect command");
            }
        }

    }
}
