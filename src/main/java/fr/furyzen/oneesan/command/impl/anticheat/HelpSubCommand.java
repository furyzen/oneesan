package fr.furyzen.oneesan.command.impl.anticheat;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.command.sub.SubCommand;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.CommandSender;

public class HelpSubCommand extends SubCommand {

    public HelpSubCommand(ACommand parent) {
        super(parent, "help", "provide a help for the commands of the anticheat.", "help");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(format("<prefix> " + ThemeLoader.INSTANCE.get("<theme>.no-permission")));
            return true;
        }

        if(getParent().getSubCommandList().stream().noneMatch(subCommand -> sender.hasPermission(subCommand.getPermission()))) {
            sender.sendMessage(format("<prefix> " + ThemeLoader.INSTANCE.get("<theme>.no-command-found")));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder(format("<prefix> " + ThemeLoader.INSTANCE.get("<theme>.command-list")));

        getParent().getSubCommandList().stream().filter(subCommand -> sender.hasPermission(subCommand.getPermission()) && !subCommand.getName().equals("help")).forEach(subCommand -> stringBuilder.append(format(ThemeLoader.INSTANCE.get("<theme>.listing-format"))));

        sender.sendMessage(stringBuilder.toString());
        sender.sendMessage("");
        return false;
    }
}
