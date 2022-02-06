package fr.furyzen.oneesan.user.data;

import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class PlayerData {

    private final Player player;

    private double x, y, z,
            lastX, lastY, lastZ,
            motionX, motionY, motionZ, motionXZ,
            lastMotionX, lastMotionY, lastMotionZ, lastMotionXZ;

    private float yaw, pitch, deltaYaw, deltaPitch, lastYaw, lastPitch,
            lastDeltaYaw, lastDeltaPitch, accelYaw, accelPitch;

    private boolean onGroundClient, onGroundServer, collidingLiquid, collidingIce, collidingWeb, collidingClimbable, collidingGround;

    private int clientAirTicks, serverAirTicks;

    private long lastAttack, lastEntity, lastSwing;

    private long lastFlyingPacket;

    private ClientVersion clientVersion;
}
