package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;
import ru.medvedev.dictionary.records.GeneralRecord;
import ru.medvedev.dictionary.records.PropertiesRecord;
import ru.medvedev.dictionary.records.Record;

import java.util.Scanner;

/**
 * Created by Сергей on 28.04.2016.
 */
public class AddWordCommand extends AbstractCommand {

    private static final String EPOS = "Enter part of speech: ";
    private static final String ADDS = "Do you want to add sense of word? y/n";
    private static final String ADDG = "Do you want to add gender of word? y/n";

    @Override
    public void execute() {
        String wordEng = enterCommand(EWIE);
        String wordRus = enterCommand(EWIR);
        String pos = enterCommand(EPOS);
        String s = enterCommand(ADDS);
        String sense = null;
        if (s.equals("y")) {
            sense = enterCommand(ENTER);
        }
        s = enterCommand(ADDG);
        String gender = null;
        if (s.equals("y")) {
            gender = enterCommand(ENTER);
        }

        Record recordEng = new Record(-1,wordEng);
        Record recordRus = new Record(-1,wordRus);
        PropertiesRecord propertiesRecord = new PropertiesRecord(-1,pos,sense,gender);
        GeneralRecord generalRecord = new GeneralRecord(recordEng, recordRus, propertiesRecord);

        dictionary.addWord(generalRecord);
    }
}
