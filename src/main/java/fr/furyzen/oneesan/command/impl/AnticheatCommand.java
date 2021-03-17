package fr.furyzen.oneesan.command.impl;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.util.Constants;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AnticheatCommand extends ACommand {

    public AnticheatCommand() {
        super("anticheat", "oneesan.command");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(format("<prefix> " + ThemeLoader.INSTANCE.get("<theme>.no-permission")));
            return true;
        }

        sender.sendMessage(format("<prefix> " + ThemeLoader.INSTANCE.get("<theme>.tagline")));
        sender.sendMessage(String.format("   §7this server is running §c%s %s§7.", Constants.NAME.toString(), Constants.VERSION.toString()));
        sender.sendMessage("");
        return true;
    }
}
