package fr.furyzen.oneesan.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.impl.PacketSendEvent;
import com.github.retrooper.packetevents.event.impl.PostPlayerInjectEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.util.OSPacket;
import org.bukkit.entity.Player;

public class PacketListener extends PacketListenerAbstract {

	public PacketListener() {
		super(PacketListenerPriority.MONITOR, true);
	}

	@Override
	public void onPacketReceive(PacketReceiveEvent event) {
		User user = Oneesan.INSTANCE.getUserManager().getUser((Player) event.getPlayer());

		if(user != null) {
			PacketTypeCommon packetType = event.getPacketType();

			if (PacketType.Play.Client.PLAYER_FLYING.equals(packetType)) {
				user.getPositionProcessor().handle(new WrapperPlayClientPlayerFlying<>(event));
			} else if (PacketType.Play.Client.PLAYER_POSITION.equals(packetType)) {
				user.getPositionProcessor().handle(new WrapperPlayClientPlayerPosition(event));
				user.getCollisionProcessor().handle(new WrapperPlayClientPlayerPosition(event));
			} else if (PacketType.Play.Client.PLAYER_ROTATION.equals(packetType)) {
				user.getPositionProcessor().handle(new WrapperPlayClientPlayerRotation(event));
				user.getRotationProcessor().handle(new WrapperPlayClientPlayerRotation(event));
			} else if (PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION.equals(packetType)) {
				user.getPositionProcessor().handle(new WrapperPlayClientPlayerPositionAndRotation(event));
				user.getRotationProcessor().handle(new WrapperPlayClientPlayerPositionAndRotation(event));
				user.getCollisionProcessor().handle(new WrapperPlayClientPlayerPositionAndRotation(event));

			}

			user.getChecks().forEach(check ->
					check.handle(user,
							new OSPacket(event, event.getPacketType(), OSPacket.Direction.RECEIVE)));
		}
	}

	@Override
	public void onPacketSend(PacketSendEvent event) {
		User user = Oneesan.INSTANCE.getUserManager().getUser((Player) event.getPlayer());

		if(user != null) {
			user.getChecks().forEach(check ->
					check.handle(user,
							new OSPacket(event, event.getPacketType(), OSPacket.Direction.SEND)));
		}
	}

	@Override
	public void onPostPlayerInject(PostPlayerInjectEvent event) {
		User user = Oneesan.INSTANCE.getUserManager().getUser((Player) event.getPlayer());
		ClientVersion clientVersion = event.getClientVersion();

		user.getPlayerData().setClientVersion(clientVersion);
	}
}
