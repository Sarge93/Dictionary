package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 02.05.2016.
 */
public abstract class AbstractCommand implements Command {

    protected static final String EWIE = "Enter word in English: ";
    protected static final String EWIR = "Enter word in Russian: ";
    protected static final String ENTER = "Enter: ";

    protected Scanner scanner = new Scanner(System.in);
    protected Dictionary dictionary = Dictionary.getInstance();

    protected String enterCommand(String text) {
        System.out.println(text);
        String res = scanner.next();
        res = res.trim();
        return res;
    }

    public abstract void execute();

}
