package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 27.04.2016.
 */
public class FindWordCommand implements Command {
    private Scanner scanner = new Scanner(System.in);
    private Dictionary dictionary = Dictionary.getInstance();
    @Override
    public void execute() {
        System.out.println("Enter word in English: ");
        String str = scanner.next();
        str = str.trim();
        dictionary.getWordInfo(str);
    }
}
