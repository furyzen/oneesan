package fr.furyzen.oneesan.command;

import fr.furyzen.oneesan.command.impl.HelpCommand;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {

    private final List<Command> commandList;

    public CommandManager() {
        commandList = new ArrayList<>();

        commandList.add(new HelpCommand());
    }
}
