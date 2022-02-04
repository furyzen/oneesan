package fr.furyzen.oneesan.user.data;

import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class PlayerData {

    final Player player;

    double x, y, z,
            lastX, lastY, lastZ,
            motionX, motionY, motionZ,
            lastMotionX, lastMotionY, lastMotionZ;

    float yaw, pitch;

    boolean onGroundClient, onGroundServer;

    ClientVersion clientVersion;
}
