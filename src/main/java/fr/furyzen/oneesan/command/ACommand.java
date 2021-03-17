package fr.furyzen.oneesan.command;

import fr.furyzen.oneesan.command.sub.SubCommand;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class ACommand implements CommandExecutor {

    private final String name, permission;

    private final List<SubCommand> subCommandList;

    public ACommand(String name, String permission) {
        this.name = name;
        this.permission = permission;

        this.subCommandList = new ArrayList<>();
    }

    public void addSubCommand(SubCommand subCommand) {
        this.subCommandList.add(subCommand);
    }

    public List<SubCommand> getSubCommandList() {
        return subCommandList;
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    public String format(String originalString) {
        return originalString
                .replace("<prefix>", ThemeLoader.INSTANCE.get("<theme>.prefix"))
                .replace("<name>", ThemeLoader.INSTANCE.get("<theme>.name")); // QUE JE SOIS AUSSI CON
    }
}
