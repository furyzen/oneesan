package fr.furyzen.oneesan.listener;

import com.github.retrooper.packetevents.event.*;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.client.*;
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
				user.getPositionProcessor().handle(new WrapperPlayClientPlayerFlying(event));
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
			} else if (PacketType.Play.Client.INTERACT_ENTITY.equals(packetType)) {
				user.getCombatProcessor().handle(new WrapperPlayClientInteractEntity(event));
			} else if (PacketType.Play.Client.ANIMATION.equals(packetType)) {
				user.getCombatProcessor().handle();
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
