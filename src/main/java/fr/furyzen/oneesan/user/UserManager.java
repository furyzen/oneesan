package fr.furyzen.oneesan.user;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserManager {

    final Map<UUID, User> userMap;

    public UserManager() {
        this.userMap = new HashMap<>();
    }

    public void add(UUID uuid, User user) {
        this.userMap.put(uuid, user);
    }
    public void remove(User user) {
        this.userMap.remove(user.getPlayer().getUniqueId());
    }
    public void clear() {
        this.userMap.clear();
    }

    public User getUserByUUID(UUID uuid) {
        for (UUID userID : userMap.keySet()) {
            if(userID.equals(uuid)) {
                return userMap.get(userID);
            }
        }
        return null;
    }

    public User getUserByPlayer(Player player) {
        for (User user : userMap.values()) {
            if(user.getPlayer().equals(player)) {
                return user;
            }
        }
        return null;
    }
}
