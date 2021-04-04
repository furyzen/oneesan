package fr.furyzen.oneesan.user;


import fr.furyzen.oneesan.user.data.PlayerData;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class User {

    final Player player;
    final PlayerData playerData;

    public User(Player player) {
        this.player = player;
        this.playerData = new PlayerData(this);
    }
}
