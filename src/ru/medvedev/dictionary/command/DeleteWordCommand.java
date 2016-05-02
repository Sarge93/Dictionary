package ru.medvedev.dictionary.command;

import ru.medvedev.dictionary.Dictionary;

import java.util.Scanner;

/**
 * Created by Сергей on 28.04.2016.
 */
public class DeleteWordCommand extends AbstractCommand {
    @Override
    public void execute() {
        dictionary.deleteWord(enterCommand(EWIE));
    }
}
