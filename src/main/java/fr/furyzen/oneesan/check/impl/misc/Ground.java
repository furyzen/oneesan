package fr.furyzen.oneesan.check.impl.misc;

import fr.furyzen.oneesan.check.Check;
import fr.furyzen.oneesan.check.CheckState;
import fr.furyzen.oneesan.check.ICheck;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.user.data.PlayerData;
import fr.furyzen.oneesan.util.OSPacket;

@ICheck(name = "Ground", checkState = CheckState.STABLE, description = "check if the player is spoofing his ground state")
public class Ground extends Check {

	@Override
	public void handle(User user, OSPacket packet) {
		PlayerData data = user.getPlayerData();

		if(packet.isPosition()) {
			if (data.isOnGroundClient() && !data.isCollidingGround()) {
				flag(user, String.format("client=%b, server=%b", data.isOnGroundClient(), data.isOnGroundServer()));
			}
		}
	}
}