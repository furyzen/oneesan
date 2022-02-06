package fr.furyzen.oneesan.listener;

import fr.furyzen.oneesan.Oneesan;
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

        Oneesan.INSTANCE.getUserManager().add(player.getUniqueId(), user);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        User user = Oneesan.INSTANCE.getUserManager().getUser(player);
        if(user == null) return;

        Oneesan.INSTANCE.getUserManager().remove(user);
    }
}
