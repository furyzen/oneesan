package fr.furyzen.oneesan;

import fr.furyzen.oneesan.command.CommandManager;
import fr.furyzen.oneesan.configuration.Configuration;
import fr.furyzen.oneesan.listener.PacketListener;
import fr.furyzen.oneesan.listener.UserListener;
import fr.furyzen.oneesan.user.UserManager;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public enum OneeSan {
    INSTANCE;

    private JavaPlugin plugin;
    private Configuration configuration;

    private UserManager userManager;
    private CommandManager commandManager;

    OneeSan() {}

    void load(JavaPlugin plugin) {
        this.plugin = plugin;

        PacketEvents.create(plugin).getSettings()
                .compatInjector(false)
                .checkForUpdates(false)
                .backupServerVersion(ServerVersion.v_1_8_8);
        PacketEvents.get().load();
    }

    void initialize() {
        PacketEvents.get().init();

        configuration = new Configuration();
        ThemeLoader.INSTANCE.load(configuration);

        userManager = new UserManager();
        commandManager = new CommandManager(plugin);

        Bukkit.getServer().getPluginManager().registerEvents(new UserListener(), plugin);
        PacketEvents.get().registerListener(new PacketListener());
    }

    void stop() {
        PacketEvents.get().terminate();
        userManager.clear();
        configuration.save();
    }
}
