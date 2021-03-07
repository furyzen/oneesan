package fr.furyzen.oneesan.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@Getter
public abstract class ACommand implements CommandExecutor {

    private final String name, permission;

    public ACommand(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
