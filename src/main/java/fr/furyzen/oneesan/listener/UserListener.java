package fr.furyzen.oneesan.listener;

import fr.furyzen.oneesan.OneeSan;
import fr.furyzen.oneesan.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        User user = new User(player);

        OneeSan.INSTANCE.getUserManager().add(player.getUniqueId(), user);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        User user = OneeSan.INSTANCE.getUserManager().getUserByPlayer(player);
        if(user == null) return;

        OneeSan.INSTANCE.getUserManager().remove(user);
    }
}
