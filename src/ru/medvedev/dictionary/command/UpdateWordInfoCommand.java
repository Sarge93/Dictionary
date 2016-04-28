package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 28.04.2016.
 */
public class UpdateWordInfoCommand implements Command{
    private Scanner scanner = new Scanner(System.in);
    private Dictionary dictionary = Dictionary.getInstance();
    @Override
    public void execute() {
        System.out.println("Enter word in English: ");
        String wordEng = scanner.next();
        wordEng = wordEng.trim();
        System.out.println("What do you want to change? Enter name of field:    (Fields: Partofspeech, Gender, Sense) ");
        String field = scanner.next();
        field = field.trim();
        System.out.println("Enter new value of field: ");
        String value = scanner.nextLine();
        value = scanner.nextLine();
        value = value.trim();
        dictionary.updateWordInfo(wordEng,field,value);
    }
}
