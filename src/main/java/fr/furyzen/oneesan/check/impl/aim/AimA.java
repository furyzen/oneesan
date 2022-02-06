package fr.furyzen.oneesan.check.impl.aim;

import fr.furyzen.oneesan.check.Check;
import fr.furyzen.oneesan.check.CheckState;
import fr.furyzen.oneesan.check.ICheck;
import fr.furyzen.oneesan.user.User;
import fr.furyzen.oneesan.user.data.PlayerData;
import fr.furyzen.oneesan.util.MathUtil;
import fr.furyzen.oneesan.util.OSPacket;
import org.bukkit.Bukkit;

@ICheck(name = "Aim [A]", checkState = CheckState.STABLE, description = "Checks for consistent gcd values.")
public class AimA extends Check {

    @Override
    public void handle(User user, OSPacket packet) {
        if (packet.isRotation()) {
            final PlayerData data = user.getPlayerData();

            final float deltaYaw = data.getDeltaYaw();

            final float deltaPitch = data.getDeltaPitch();
            final float lastDeltaPitch = data.getLastDeltaPitch();

            final long gcd = MathUtil.getAbsoluteGcd(deltaPitch, lastDeltaPitch);

            if (Math.abs(deltaPitch) > 0
                    && deltaYaw > 1.25f
                    && data.getAccelYaw() > 0.125) {
                if (Math.abs(gcd) < 130172L) {
                    if (++buffer > 6)
                        flag(user, String.format("gcd=%d", gcd));

                } else if (buffer > 0) buffer -= 0.15;

            }
        }

    }
}
