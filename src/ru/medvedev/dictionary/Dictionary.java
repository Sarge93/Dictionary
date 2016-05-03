package ru.medvedev.dictionary;

import ru.medvedev.dictionary.dao.ConcreteOracleController;
import ru.medvedev.dictionary.records.GeneralRecord;

import java.util.List;

/**
 * Created by Сергей on 25.04.2016.
 */
public class Dictionary {
    private static Dictionary dictionary;
    private static ConcreteOracleController userController;
    private static Locale locale;

    static {
        userController = new ConcreteOracleController();
    }

    private Dictionary() {
    }

    public static Dictionary getInstance() {
        if (dictionary == null) {
            dictionary = new Dictionary();
        }
        return dictionary;
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        Dictionary.locale = locale;
    }

    public List<GeneralRecord> getAllWords() {
        return userController.getAll();
    }

    public void addWord(GeneralRecord generalRecord) {
        if (userController.create(generalRecord)) {
            System.out.println("Create row: Complete");
        } else {
            System.out.println("Create row: Failed");
        }
    }

    public void getWordInfo(String word) {
        GeneralRecord generalRecord = userController.getEntityByName(word);
        if (generalRecord != null) {
            System.out.println(generalRecord);
        } else {
            System.out.println("Word is not found");
        }

    }

    public void deleteWord(String word) {
        if (userController.delete(word)) {
            System.out.println("Delete word: Complete");
        } else {
            System.out.println("Delete word: Failed");
        }
    }

    public void updateWordInfo(String word, String field, String value) {
        if (userController.update(word,field.toUpperCase(),value)) {
            System.out.println("Update word: Complete");
        } else {
            System.out.println("Update word: Failed");
        }
    }
}
