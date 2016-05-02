package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 28.04.2016.
 */
public class UpdateWordInfoCommand extends AbstractCommand {

    private static final String WDYWTC = "What do you want to change? Enter name of field:    (Fields: Partofspeech, Gender, Sense) ";
    private static final String NVAL = "Enter new value of field: ";
    @Override
    public void execute() {
        String wordEng = enterCommand(EWIE);
        String field = enterCommand(WDYWTC);
        String value = enterCommand(NVAL);
        dictionary.updateWordInfo(wordEng,field,value);
    }
}
