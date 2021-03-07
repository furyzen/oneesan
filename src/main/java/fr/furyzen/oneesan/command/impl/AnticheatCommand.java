package fr.furyzen.oneesan.command.impl;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.util.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AnticheatCommand extends ACommand {

    public AnticheatCommand() {
        super("anticheat", "oneesan.command");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(Constants.PREFIX + "§cYou do not have the permission to perform this command.");
            return true;
        }

        sender.sendMessage(String.format("  §5%s §7- §dPlayer-kun§7, §7ready to §dpunish §7some bad boys ? §d<3", Constants.NAME.toString()));
        sender.sendMessage(String.format("   §7this server is running §d%s %s§8.", Constants.NAME.toString(), Constants.VERSION.toString()));
        sender.sendMessage("");
        return true;
    }
}
