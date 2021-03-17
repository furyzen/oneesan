package fr.furyzen.oneesan.util.theme;

import fr.furyzen.oneesan.OneeSan;
import fr.furyzen.oneesan.configuration.Configuration;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public enum ThemeLoader {
    INSTANCE;

    private FileConfiguration themeConfiguration;
    private String theme;

    public void load(Configuration configuration) {
        File file = new File(OneeSan.INSTANCE.getPlugin().getDataFolder(), "theme.yml");
        theme = configuration.getConfig().getString("theme");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            OneeSan.INSTANCE.getPlugin().saveResource("theme.yml", false);
        }

        try {
            (this.themeConfiguration = new YamlConfiguration()).load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String path) {
        return ChatColor.translateAlternateColorCodes('&', themeConfiguration.getString(path.replace("<theme>", theme)));
    }
}
