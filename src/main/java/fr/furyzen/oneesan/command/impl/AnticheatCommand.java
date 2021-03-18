package fr.furyzen.oneesan.command.impl;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.command.impl.anticheat.DebugSubCommand;
import fr.furyzen.oneesan.command.impl.anticheat.HelpSubCommand;
import fr.furyzen.oneesan.command.sub.SubCommand;
import fr.furyzen.oneesan.util.Constants;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AnticheatCommand extends ACommand {

    public AnticheatCommand() {
        super("anticheat", "oneesan");

        addSubCommand(new HelpSubCommand(this));
        addSubCommand(new DebugSubCommand(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(getPermission() + ".command")) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.no-permission")));
            return true;
        }

        //TODO: help subcommand
        if(args.length > 0) {
            if(getSubCommandList().isEmpty()) return true;

            for(SubCommand subCommand : getSubCommandList()) {
                if(subCommand.getName().equalsIgnoreCase(args[0])) {
                    return subCommand.execute(sender, args);
                }
                //TODO: no commands message thingy
            }

            sender.sendMessage(format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.invalid-command")));
            return true;
        }

        sender.sendMessage(format("<prefix>" + ThemeLoader.INSTANCE.get("<theme>.tagline")));
        sender.sendMessage(String.format("   §7this server is running §c%s %s§7.", Constants.NAME.toString(), Constants.VERSION.toString()));
        sender.sendMessage("");
        return true;
    }
}
