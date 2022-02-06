package fr.furyzen.oneesan.command.impl;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.command.Command;
import fr.furyzen.oneesan.command.ICommand;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@ICommand(permission = "oneesan.alert", names = { "alert", "alerts", "notify" }, description = "Toggle if you receive flags or not.")
public class AlertCommand extends Command {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.no-permission")));
            return true;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage(format("<prefix>" + ThemeLoader.getInstance().get("<theme>.console-execute")));
            return false;
        }
        User user = Oneesan.INSTANCE.getUserManager().getUser((Player) sender);

        user.alerts = !user.alerts;
        user.getPlayer().sendMessage(String.format(format("<prefix> " + ThemeLoader.getInstance().get("<theme>.alerts-toggled")), (user.alerts ? "§aon" : "§coff")));
        return true;
    }
}
