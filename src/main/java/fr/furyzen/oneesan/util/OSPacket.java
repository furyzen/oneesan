package fr.furyzen.oneesan.util;

import com.github.retrooper.packetevents.event.PacketEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import lombok.Data;

@Data
public class OSPacket {

    private final PacketEvent event;
    private final PacketTypeCommon packetType;
    private final Direction direction;

    public enum Direction {
        RECEIVE, SEND;
    }
}
