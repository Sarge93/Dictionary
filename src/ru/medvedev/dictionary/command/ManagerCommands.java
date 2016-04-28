package ru.medvedev.dictionary.command;

/**
 * Created by Сергей on 28.04.2016.
 */
import java.util.ArrayList;
public class ManagerCommands {
    private ArrayList<Command> listCommands= new ArrayList<Command>();
    public void addCommand(Command command){
        listCommands.add(command);
    }
    public void run(){
        for(Command command:listCommands){
            command.execute();
        }
    }
    public void clearListCommands() {
        listCommands.clear();
    }
}
