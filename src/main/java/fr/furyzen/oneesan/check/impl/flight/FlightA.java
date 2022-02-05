package fr.furyzen.oneesan.check.impl.flight;

import fr.furyzen.oneesan.check.Check;
import fr.furyzen.oneesan.check.CheckState;
import fr.furyzen.oneesan.check.ICheck;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.user.data.PlayerData;
import fr.furyzen.oneesan.util.OSPacket;

@ICheck(name = "Flight [A]", checkState = CheckState.STABLE, description = "Simple & classic flight check.")
public class FlightA extends Check {

    @Override
    public void handle(User user, OSPacket packet) {
        final PlayerData data = user.getPlayerData();

        if (packet.isPosition() && !data.isOnGroundClient()) {

            final boolean isInMidAir = data.getClientAirTicks() > 11;
            final double predictedY = Math.abs((data.getLastMotionY() - 0.08) * 0.98F) > 0.005 ? (data.getLastMotionY() - 0.08) * 0.98F : 0;

            if (Math.abs(predictedY - data.getMotionY()) > 0.001 && isInMidAir && data.getMotionXZ() > 0.01) {
                if (++buffer > 4)
                    flag(user, String.format("diff=%f", Math.abs(predictedY - data.getMotionY())));
            } else if (buffer > 0) buffer -= 0.1D;
        }
    }

}
