package fr.furyzen.oneesan.user;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Getter
public class UserManager {

    private final Map<UUID, User> userMap;

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

    public User getUser(UUID uuid) {
        return userMap.entrySet().stream().filter(entry ->
                entry.getKey().equals(uuid)).map(Map.Entry::getValue).findFirst().orElse(null);
    }

    public User getUser(Player player) {
        return userMap.values().stream().filter(user ->
                user.getPlayer().equals(player)).findFirst().orElse(null);
    }
}
