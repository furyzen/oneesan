package fr.furyzen.oneesan.util;

import com.github.retrooper.packetevents.event.PacketEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import fr.furyzen.oneesan.check.impl.packet.PacketA;
import lombok.Data;

@Data
public class OSPacket {

    private final PacketEvent event;
    private final PacketTypeCommon packetType;
    private final Direction direction;

    public enum Direction {
        RECEIVE, SEND;
    }

    public boolean isRot() {
        return packetType == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION || packetType == PacketType.Play.Client.PLAYER_ROTATION;
    }

    public boolean isMove() {
        return packetType == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION || packetType == PacketType.Play.Client.PLAYER_POSITION;
    }
}
