package ru.medvedev.dictionary;

import ru.medvedev.dictionary.records.GeneralRecord;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Сергей on 27.04.2016.
 */
public class Menu {
    private Menu() {}

    private static void findWord() {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = Dictionary.getInstance();
        System.out.println("Enter word in English: ");
        String str = scanner.next();
        str = str.trim();
        dictionary.getWordInfo(str);
    }

    private static void addWord() {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = Dictionary.getInstance();
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

    private static void updateWord() {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = Dictionary.getInstance();
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

    private static void deleteWord() {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = Dictionary.getInstance();
        System.out.println("Enter word in English: ");
        String wordEng = scanner.next();
        wordEng = wordEng.trim();
        dictionary.deleteWord(wordEng);
    }

    private static void showAllWords() {
        Dictionary dictionary = Dictionary.getInstance();
        List<GeneralRecord> allWords = dictionary.getAllWords();
        for (GeneralRecord allWord : allWords) {
            System.out.println(allWord);
        }
    }

    public static void getMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dictionary");
        System.out.println("-----------------------------------");
        while(true) {
            System.out.println("Choose command:\n 1. Find word\n 2. Add word\n 3. Update word info \n 4. Delete word \n 5. Show all words\n 6. Exit");
            System.out.println();
            System.out.println("Enter: ");
            int ch = scanner.nextInt();
            if (ch == 1) {
                findWord();
            } else if (ch == 2) {
                addWord();
            } else if (ch == 3) {
                updateWord();
            } else if (ch == 4) {
                deleteWord();
            } else if (ch == 5) {
                showAllWords();
            } else if (ch == 6) {
                break;
            } else {
                System.out.println("Incorrect command");
            }
        }
    }
}
