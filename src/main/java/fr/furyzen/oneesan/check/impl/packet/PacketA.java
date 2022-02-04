package fr.furyzen.oneesan.check.impl.packet;

import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import fr.furyzen.oneesan.check.Check;
import fr.furyzen.oneesan.check.CheckState;
import fr.furyzen.oneesan.check.ICheck;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.user.data.PlayerData;
import fr.furyzen.oneesan.util.OSPacket;

@ICheck(name = "Packet [A]", checkState = CheckState.STABLE, description = "check if the player's rotations goes out of bounds.")
public class PacketA extends Check {

    @Override
    public void handle(User user, OSPacket packet) {
        PlayerData data = user.getPlayerData();

        if(packet.getPacketType().equals(PacketType.Play.Client.PLAYER_FLYING)) {
            float[] rotations = new float[] { data.getYaw(), data.getPitch() };

            if (Math.abs(rotations[1]) > 90) {
                flag(user, String.format("yaw=%f,pitch=%f", rotations[0], rotations[1]));
            }
        }
    }
}