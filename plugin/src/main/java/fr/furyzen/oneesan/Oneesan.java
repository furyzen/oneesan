package fr.furyzen.oneesan;

import org.bukkit.plugin.java.JavaPlugin;

public enum Oneesan {
    INSTANCE;

    private JavaPlugin plugin;

    void load(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    void initialize() {}

    void stop() {}
}
