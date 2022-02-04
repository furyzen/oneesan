package fr.furyzen.oneesan.util.theme;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.configuration.Configuration;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

//TODO: make a thememanager, i guess lol ?
public enum ThemeLoader {
    INSTANCE;

    private final File themeFile;

    private FileConfiguration themeConfiguration;
    private String theme;


    @SneakyThrows
    ThemeLoader() {
        themeFile = new File(Oneesan.INSTANCE.getPlugin().getDataFolder(), "theme.yml");
        if (!themeFile.exists())
            if(themeFile.getParentFile().mkdirs())
                Oneesan.INSTANCE.getPlugin().saveResource("theme.yml", false);
    }
    @SneakyThrows
    public void load(Configuration configuration) {
        theme = configuration.getConfiguration().getString("theme");

        (themeConfiguration = new YamlConfiguration()).load(this.themeFile);
    }

    public String get(String path) {
        return ChatColor.translateAlternateColorCodes('&', themeConfiguration.getString(path.replace("<theme>", theme)));
    }
}
