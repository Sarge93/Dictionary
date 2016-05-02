package ru.medvedev.dictionary.command;

/**
 * Created by Сергей on 28.04.2016.
 */
import java.util.ArrayList;
import java.util.List;

public class ManagerCommands {
    private List<Command> listCommands= new ArrayList<Command>();
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
