package fr.furyzen.oneesan.command.impl;

import fr.furyzen.oneesan.command.Command;
import fr.furyzen.oneesan.command.ICommand;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.CommandSender;

@ICommand(permission = "oneesan.help", names = { "help", "h" }, description = "get a list of the commands.")
public class HelpCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.no-permission")));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.command-list")) + "\n");

        oneesan.getCommandManager().getCommandList().stream().filter(command ->
                sender.hasPermission(command.getPermission())).forEach(command ->
                stringBuilder.append(String.format(format(ThemeLoader.getInstance().get("<theme>.command-listing-format")), command.getNames()[0], command.getDescription())).append("\n"));

        sender.sendMessage(stringBuilder.toString());
        sender.sendMessage("");
        return true;
    }
}
