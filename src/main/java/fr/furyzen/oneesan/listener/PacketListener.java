package fr.furyzen.oneesan.listener;

import fr.furyzen.oneesan.OneeSan;
import fr.furyzen.oneesan.user.User;
import io.github.retrooper.packetevents.event.PacketListenerDynamic;
import io.github.retrooper.packetevents.event.impl.PostPlayerInjectEvent;
import io.github.retrooper.packetevents.event.priority.PacketEventPriority;
import io.github.retrooper.packetevents.utils.player.ClientVersion;

public class PacketListener extends PacketListenerDynamic {

    public PacketListener() {
        super(PacketEventPriority.MONITOR);
    }

    @Override
    public void onPostPlayerInject(PostPlayerInjectEvent event) {
        User user = OneeSan.INSTANCE.getUserManager().getUserByPlayer(event.getPlayer());
        ClientVersion clientVersion = event.getClientVersion();

        user.getPlayerData().setClientVersion(clientVersion);
    }
}
