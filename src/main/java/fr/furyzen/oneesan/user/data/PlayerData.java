package fr.furyzen.oneesan.user.data;

import fr.furyzen.oneesan.user.User;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerData {

    final User user;

    public PlayerData(User user) {
        this.user = user;
    }

    ClientVersion clientVersion;
}
