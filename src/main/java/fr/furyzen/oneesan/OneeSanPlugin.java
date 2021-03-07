package fr.furyzen.oneesan;

import org.bukkit.plugin.java.JavaPlugin;

public class OneeSanPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        OneeSan.INSTANCE.initialize(this);
    }

    @Override
    public void onDisable() {
        OneeSan.INSTANCE.stop();
    }
}
