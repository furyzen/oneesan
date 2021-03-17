package fr.furyzen.oneesan.command.impl.anticheat;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.command.sub.SubCommand;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.CommandSender;

public class DebugSubCommand extends SubCommand {

    public DebugSubCommand(ACommand parent) {
        super(parent, "debug", "debug");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission() + ".command")) {
            sender.sendMessage(format("<prefix> " + ThemeLoader.INSTANCE.get("<theme>.no-permission")));
            return true;
        }

        sender.sendMessage(format("§cUsing theme: §4<name>."));
        return true;
    }
}
