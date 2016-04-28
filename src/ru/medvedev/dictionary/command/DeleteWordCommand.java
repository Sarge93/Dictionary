package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 28.04.2016.
 */
public class DeleteWordCommand implements Command {
    private Scanner scanner = new Scanner(System.in);
    private Dictionary dictionary = Dictionary.getInstance();

    @Override
    public void execute() {
        System.out.println("Enter word in English: ");
        String wordEng = scanner.next();
        wordEng = wordEng.trim();
        dictionary.deleteWord(wordEng);
    }
}
