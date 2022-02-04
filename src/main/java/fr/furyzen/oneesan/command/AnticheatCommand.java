package fr.furyzen.oneesan.command;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.util.Constants;
import fr.furyzen.oneesan.util.theme.ThemeHelper;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class AnticheatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("oneesan.command")) {
            sendInfoMessage(sender);
            return true;
        }
        if (args.length > 0) {
            if (Oneesan.INSTANCE.getCommandManager().getCommandList().isEmpty()) {
                sender.sendMessage(ThemeHelper.format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.no-command-found")));
                return true;
            }
            for (fr.furyzen.oneesan.command.Command command : Oneesan.INSTANCE.getCommandManager().getCommandList()) {
                if(Arrays.asList(command.getNames()).contains(args[0])) {
                    return command.execute(sender, args);
                }
            }

            sender.sendMessage(ThemeHelper.format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.invalid-command")));
            return true;
        }

        sendInfoMessage(sender);
        return true;
    }

    // i had no ideas for the method name lol
    void sendInfoMessage(CommandSender sender) {
        sender.sendMessage(ThemeHelper.format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.tagline")));
        sender.sendMessage(String.format("   §7this server is running §c%s %s§7.", Constants.NAME, Constants.VERSION));
        sender.sendMessage("");
    }


}
