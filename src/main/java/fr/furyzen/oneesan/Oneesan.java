package fr.furyzen.oneesan;

import com.github.retrooper.packetevents.PacketEvents;
import fr.furyzen.oneesan.command.CommandManager;
import fr.furyzen.oneesan.command.AnticheatCommand;
import fr.furyzen.oneesan.configuration.Configuration;
import fr.furyzen.oneesan.listener.PacketListener;
import fr.furyzen.oneesan.listener.UserListener;
import fr.furyzen.oneesan.user.UserManager;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public enum Oneesan {
    INSTANCE;

    private JavaPlugin plugin;
    private Configuration configuration;

    private UserManager userManager;
    private CommandManager commandManager;

    void load(JavaPlugin plugin) {
        this.plugin = plugin;

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(getPlugin()));
        PacketEvents.getAPI().load();
    }

    void initialize() {
        configuration = new Configuration();
        ThemeLoader.INSTANCE.load(this.configuration);

        userManager = new UserManager();

        getPlugin().getCommand("oneesan").setExecutor(new AnticheatCommand());
        commandManager = new CommandManager();

        Bukkit.getServer().getPluginManager().registerEvents(new UserListener(), plugin);
        PacketEvents.getAPI().getEventManager().registerListener(new PacketListener());
        PacketEvents.getAPI().init();
    }

    void stop() {
        PacketEvents.getAPI().terminate();

        userManager.clear();
        configuration.save();
    }
}
