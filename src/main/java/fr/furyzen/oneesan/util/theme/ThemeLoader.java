package fr.furyzen.oneesan.util.theme;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.configuration.Configuration;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

//TODO: make a thememanager, i guess lol ?
public enum ThemeLoader {
    INSTANCE;

    private File themeFile;

    private FileConfiguration themeConfiguration;
    private String theme;


    private void saveFile() {
        this.themeFile = new File(Oneesan.INSTANCE.getPlugin().getDataFolder(), "theme.yml");
        this.themeConfiguration = YamlConfiguration.loadConfiguration(themeFile);

        try {
            themeConfiguration.save(themeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void load(Configuration configuration) {
        saveFile();

        if(themeFile.length() == 0) {
            themeConfiguration.options().copyDefaults(true);
        }

        theme = configuration.getConfiguration().getString("theme");

        (themeConfiguration = new YamlConfiguration()).load(this.themeFile);



    }

    public String get(String path) {
        return ChatColor.translateAlternateColorCodes('&', themeConfiguration.getString(path.replace("<theme>", theme)));
    }
}
