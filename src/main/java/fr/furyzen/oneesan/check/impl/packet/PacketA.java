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

        if(packet.isRot()) {
            final float pitch = data.getPitch();

            if (Math.abs(pitch) > 90) {
                flag(user, String.format("pitch=%f", pitch));
            }
        }
    }
}