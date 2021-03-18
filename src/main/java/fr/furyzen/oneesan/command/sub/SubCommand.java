package fr.furyzen.oneesan.command.sub;

import fr.furyzen.oneesan.command.ACommand;
import fr.furyzen.oneesan.util.theme.ThemeHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

@Getter
@AllArgsConstructor
public abstract class SubCommand {

    private ACommand parent;
    private String name, description, permission;


    public String getPermission() {
        return permission;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    protected String format(String originalString) {
        return ThemeHelper.format(originalString);
    } //why do i did that
}
