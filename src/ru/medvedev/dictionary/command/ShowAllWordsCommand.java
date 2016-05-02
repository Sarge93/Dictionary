package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;
import ru.medvedev.dictionary.records.GeneralRecord;

import java.util.List;

/**
 * Created by Сергей on 28.04.2016.
 */
public class ShowAllWordsCommand extends AbstractCommand {

    @Override
    public void execute() {
        List<GeneralRecord> allWords = dictionary.getAllWords();
        for (GeneralRecord allWord : allWords) {
            System.out.println(allWord);
        }
    }
}
