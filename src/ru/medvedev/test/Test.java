package ru.medvedev.test;

import ru.medvedev.dictionary.Dictionary;
import ru.medvedev.dictionary.records.GeneralRecord;

import java.util.List;

/**
 * Created by Сергей on 25.04.2016.
 */
public class Test {
    public static void main(String[] args) {
        Dictionary dictionary = Dictionary.getInstance();
        dictionary.updateWordInfo("Lama","Gender","женский");
    }
}
