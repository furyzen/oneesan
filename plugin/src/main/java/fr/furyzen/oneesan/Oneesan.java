package fr.furyzen.oneesan;

import fr.furyzen.oneesan.registry.CommandRegistry;
import fr.furyzen.oneesan.utilities.Constants;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public enum Oneesan {
    INSTANCE;

    private JavaPlugin plugin;

    private Logger logger;

    private CommandRegistry commandRegistry;

    void load(JavaPlugin plugin) {
        this.plugin = plugin;

        this.logger = plugin.getSLF4JLogger();

        this.commandRegistry = new CommandRegistry(plugin);
    }

    void initialize() {
        commandRegistry.initialize();
    }

    void stop() {}

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }
}
