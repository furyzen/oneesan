package fr.furyzen.oneesan.command.impl.anticheat;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.command.sub.SubCommand;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.CommandSender;

public class HelpSubCommand extends SubCommand {

    public HelpSubCommand(ACommand parent) {
        super(parent, "help", "provide a list of the commands of the anticheat", "oneesan.help");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.no-permission")));
            return true;
        }

        if(getParent().getSubCommandList().stream().filter(subCommand -> !subCommand.getName().equals("help")).noneMatch(subCommand -> sender.hasPermission(subCommand.getPermission()))) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.no-command-found")));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder(format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.command-list")) + "\n");

        getParent().getSubCommandList().stream().filter(subCommand -> sender.hasPermission(subCommand.getPermission()) && !subCommand.getName().equals("help")).forEach(subCommand -> stringBuilder.append(String.format(format(ThemeLoader.INSTANCE.get("<theme>.command-listing-format")), subCommand.getName(), subCommand.getDescription()) + "\n"));

        sender.sendMessage(stringBuilder.toString());
        sender.sendMessage("");
        return true;
    }
}
