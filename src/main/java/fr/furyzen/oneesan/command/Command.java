package fr.furyzen.oneesan.command;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.util.theme.ThemeHelper;
import lombok.Getter;
import org.bukkit.command.CommandSender;

@Getter
public abstract class Command {

    private final String permission = getClass().getAnnotation(ICommand.class).permission(),
            description = getClass().getAnnotation(ICommand.class).description();
    private final String[] names = getClass().getAnnotation(ICommand.class).names();

    public abstract boolean execute(CommandSender sender, String[] args);

    protected String format(String originalString) {
        return ThemeHelper.format(originalString);
    }

    protected Oneesan oneesan = Oneesan.INSTANCE;
}