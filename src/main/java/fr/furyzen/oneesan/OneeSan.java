package fr.furyzen.oneesan;

import fr.furyzen.oneesan.command.CommandManager;
import fr.furyzen.oneesan.configuration.Configuration;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public enum OneeSan {
    INSTANCE;

    private JavaPlugin plugin;
    private Configuration configuration;

    private CommandManager commandManager;

    OneeSan() {}

    void initialize(JavaPlugin plugin) {
        this.plugin = plugin;

        configuration = new Configuration();
        ThemeLoader.INSTANCE.load(configuration);
        commandManager = new CommandManager(plugin);
    }

    void stop() {
        configuration.save();
    }
}
