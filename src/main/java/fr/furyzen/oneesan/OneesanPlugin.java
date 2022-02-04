package fr.furyzen.oneesan;

import org.bukkit.plugin.java.JavaPlugin;

public class OneesanPlugin extends JavaPlugin {

    @Override public void onLoad() {
        Oneesan.INSTANCE.load(this);
    }
    @Override public void onEnable() {
        Oneesan.INSTANCE.initialize();
    }
    @Override public void onDisable() {
        Oneesan.INSTANCE.stop();
    }
}
