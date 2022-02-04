package fr.furyzen.oneesan.check;

import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.util.OSPacket;
import fr.furyzen.oneesan.util.TimeHelper;
import fr.furyzen.oneesan.util.theme.ThemeLoader;
import lombok.Getter;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

@Getter
public abstract class Check {

    private final String name = getClass().getAnnotation(ICheck.class).name();
    private final CheckState checkState = getClass().getAnnotation(ICheck.class).checkState();

    protected TimeHelper flagTimer;

    public Check() {
        flagTimer = new TimeHelper();
    }

    public void flag(User user, String detectionInfo) {
        if(user.getPlayer().getGameMode().equals(GameMode.CREATIVE) || user.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) return;

        boolean experimental = checkState.equals(CheckState.EXPERIMENTAL);

        if(!experimental) user.addViolation(this);

        if(flagTimer.hasPassed(Oneesan.INSTANCE.getConfiguration().getConfiguration().getInt("flagTimer"))) {
            Bukkit.getOnlinePlayers().stream().map(usr -> Oneesan.INSTANCE.getUserManager().getUser(usr)).filter(User::isAlerts).forEach(usr -> {
                String message = String.format(ThemeLoader.INSTANCE.get("<theme>.flag-message"), user.getPlayer().getName(), getName(),
                        experimental ? ThemeLoader.INSTANCE.get("<theme>.flag-experimental") : String.format(ThemeLoader.INSTANCE.get("<theme>.flag-violationcounter"), user.getViolation(this)));

                TextComponent component = new TextComponent(message);
                TextComponent hoverComponent = new TextComponent(String.format("§d%s§r", detectionInfo));
                component.setHoverEvent(
                        new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { hoverComponent}));
                usr.getPlayer().spigot().sendMessage(component);
            });
            flagTimer.reset();
        }
    }

    public abstract void handle(User user, OSPacket packet);
}
