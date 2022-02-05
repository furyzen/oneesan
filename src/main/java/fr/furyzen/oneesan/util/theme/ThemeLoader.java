package fr.furyzen.oneesan.util.theme;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.configuration.Configuration;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

//TODO: make a thememanager, i guess lol ?
public class ThemeLoader {

    @Getter private static ThemeLoader instance;

    private final File themeFile;
    private FileConfiguration themeConfiguration;
    private String theme;

    public ThemeLoader() {
        instance = this;

        themeFile = new File(Oneesan.INSTANCE.getPlugin().getDataFolder(), "theme.yml");
        if (!themeFile.exists()) {
            themeFile.getParentFile().mkdirs();
            Oneesan.INSTANCE.getPlugin().saveResource("theme.yml", false);
        }
    }

    @SneakyThrows
    public void load(Configuration configuration) {
        (themeConfiguration = new YamlConfiguration()).load(this.themeFile);

        theme = configuration.getConfiguration().getString("theme");
    }

    @SneakyThrows
    public void save() {
        themeConfiguration.save(this.themeFile);
        reload();
    }

    @SneakyThrows
    public void reload() {
        themeConfiguration = YamlConfiguration.loadConfiguration(this.themeFile);
    }

    public String get(String path) {
        return ChatColor.translateAlternateColorCodes('&', themeConfiguration.getString(path.replace("<theme>", theme)));
    }
}
