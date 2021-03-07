package fr.furyzen.oneesan.configuration;

import fr.furyzen.oneesan.OneeSan;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Configuration {

    private File file;
    @Getter
    private FileConfiguration config;

    public Configuration() {
        file = new File(OneeSan.INSTANCE.getPlugin().getDataFolder(),  "config.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            OneeSan.INSTANCE.getPlugin().saveResource("config.yml", false);
        }

        try {
            (this.config = new YamlConfiguration()).load(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(this.file);
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config = YamlConfiguration.loadConfiguration(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
