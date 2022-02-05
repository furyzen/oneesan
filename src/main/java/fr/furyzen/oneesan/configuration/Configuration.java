package fr.furyzen.oneesan.configuration;

import fr.furyzen.oneesan.Oneesan;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Configuration {

    private final File configurationFile;
    @Getter private FileConfiguration configuration;

    @SneakyThrows
    public Configuration() {
        configurationFile = new File(Oneesan.INSTANCE.getPlugin().getDataFolder(), "config.yml");
        if (!configurationFile.exists()) {
            configurationFile.getParentFile().mkdirs();
            Oneesan.INSTANCE.getPlugin().saveResource("config.yml", false);
        }
    }

    @SneakyThrows
    public void load() {
        (configuration = new YamlConfiguration()).load(this.configurationFile);
    }

    @SneakyThrows
    public void save() {
        configuration.save(this.configurationFile);
        reload();
    }

    @SneakyThrows
    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
    }
}
