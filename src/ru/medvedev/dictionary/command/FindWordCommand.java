package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 27.04.2016.
 */
public class FindWordCommand extends AbstractCommand {
    @Override
    public void execute() {
        dictionary.getWordInfo(enterCommand(EWIE));
    }
}
