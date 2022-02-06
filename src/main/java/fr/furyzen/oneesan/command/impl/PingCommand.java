package fr.furyzen.oneesan.command.impl;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.command.Command;
import fr.furyzen.oneesan.command.ICommand;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@ICommand(permission = "oneesan.ping", names = { "ping", "p" }, description = "Get your ping or another's.")
public class PingCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.no-permission")));
            return true;
        }

        if(args.length == 2) {
            User targetUser = Oneesan.INSTANCE.getUserManager().getUser(args[1]);
            if(targetUser == null) {
                sender.sendMessage(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.unknown-player")));
                return false;
            }

            long targetPing = ((CraftPlayer)targetUser.getPlayer()).getHandle().ping;
            sender.sendMessage(String.format(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.ping-response")), targetUser.getPlayer().getName(), targetPing));
        } else {
            if(!(sender instanceof Player)) {
                sender.sendMessage(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.console-execute")));
                return false;
            }
            User user = Oneesan.INSTANCE.getUserManager().getUser((Player) sender);

            long ping = ((CraftPlayer)user.getPlayer()).getHandle().ping;
            sender.sendMessage(String.format(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.self-ping-response")), ping));
        }
        return true;
    }
}
