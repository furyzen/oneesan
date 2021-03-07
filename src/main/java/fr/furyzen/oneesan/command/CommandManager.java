package fr.furyzen.oneesan.command;

import fr.furyzen.oneesan.command.impl.AnticheatCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    final JavaPlugin plugin;

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;

        addCommand(new AnticheatCommand());
    }

    public void addCommand(ACommand command) {
        plugin.getCommand(command.getName()).setExecutor(command);
    }
}
