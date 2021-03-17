package fr.furyzen.oneesan.command.sub;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Getter
@AllArgsConstructor
public abstract class SubCommand {

    private ACommand parent;
    private String name, permission;


    public String getPermission() {
        return parent.getPermission() + "." + permission;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public String format(String originalString) {
        return originalString
                .replace("<prefix>", ThemeLoader.INSTANCE.get("<theme>.prefix"))
                .replace("<name>", ThemeLoader.INSTANCE.get("<theme>.name")); // QUE JE SOIS AUSSI CON
    }
}
