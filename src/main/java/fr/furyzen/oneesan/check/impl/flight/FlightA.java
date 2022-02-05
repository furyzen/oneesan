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
        if (packet.isPosition()) {
            final PlayerData data = user.getPlayerData();

            final boolean isInAir = data.getClientAirTicks() > 3;
            final boolean isInMidAir = data.getClientAirTicks() > 10;

            final double predictedY = Math.abs((data.getLastMotionY() - 0.08) * 0.98F) > 0.005 ? (data.getLastMotionY() - 0.08) * 0.98F : 0;

            final double threshold = isInMidAir ? 0.001 : 0.033; //yeah ik, lazy 0.03 fix

            if (Math.abs(predictedY - data.getMotionY()) > threshold && isInAir && data.getMotionXZ() > 0.01) {
                if (++buffer > 4)
                    flag(user, String.format("diff=%f", Math.abs(predictedY - data.getMotionY())));
            } else if(buffer > 0) buffer -= 0.1D;
        }
    }
}
