package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 28.04.2016.
 */
public class AddWordCommand implements Command {

    private Scanner scanner = new Scanner(System.in);
    private Dictionary dictionary = Dictionary.getInstance();

    @Override
    public void execute() {
        System.out.println("Enter word in English: ");
        String wordEng = scanner.next();
        wordEng = wordEng.trim();
        System.out.println("Enter word in Russian: ");
        String wordRus = scanner.next();
        wordRus = wordRus.trim();
        System.out.println("Enter part of speech: ");
        String pos = scanner.next();
        pos = pos.trim();
        System.out.println("Do you want to add sense of word? y/n");
        String s = scanner.next();
        s = s.trim();
        String sense = null;
        if (s.equals("y")) {
            System.out.println("Enter: ");
            sense = scanner.next();
            sense = sense.trim();
        }
        System.out.println("Do you want to add gender of word? y/n");
        s = scanner.next();
        s = s.trim();
        String gender = null;
        if (s.equals("y")) {
            System.out.println("Enter: ");
            gender = scanner.next();
            gender = gender.trim();
        }
        dictionary.addWord(wordEng,wordRus,pos,gender,sense);
    }
}
