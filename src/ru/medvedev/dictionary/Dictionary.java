package ru.medvedev.dictionary;

import ru.medvedev.dictionary.dao.UserController;
import ru.medvedev.dictionary.records.GeneralRecord;

import java.util.List;

/**
 * Created by Сергей on 25.04.2016.
 */
public class Dictionary {
    private static Dictionary dictionary;
    private static UserController userController;

    static {
        userController = new UserController();
    }

    private Dictionary() {
    }

    public static Dictionary getInstance() {
        if (dictionary == null) {
            dictionary = new Dictionary();
        }
        return dictionary;
    }

    public List<GeneralRecord> getAllWords() {
        return userController.getAll();
    }

    public void addWord(String wordEng, String wordRus, String partOfSpeech, String gender, String sense) {
        RecordEng recordEng = new RecordEng(-1, wordEng, partOfSpeech, sense);
        RecordRus recordRus = new RecordRus(-1, wordRus, gender);
        GeneralRecord generalRecord = new GeneralRecord(recordRus,recordEng);
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
