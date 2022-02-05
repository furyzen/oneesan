package fr.furyzen.oneesan.user;


import fr.furyzen.oneesan.check.Check;
import fr.furyzen.oneesan.check.impl.flight.FlightA;
import fr.furyzen.oneesan.check.impl.packet.PacketA;
import fr.furyzen.oneesan.user.data.PlayerData;
import fr.furyzen.oneesan.user.processor.CollisionProcessor;
import fr.furyzen.oneesan.user.processor.PositionProcessor;
import fr.furyzen.oneesan.user.processor.RotationProcessor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class User {

    private final Player player;
    private final PlayerData playerData;

    private final PositionProcessor positionProcessor;
    private final RotationProcessor rotationProcessor;
    private final CollisionProcessor collisionProcessor;

    private final Map<Check, Integer> violations;
    private final List<Check> checks;

    public boolean alerts;

    public User(Player player) {
        this.player = player;
        playerData = new PlayerData(player);

        checks = new ArrayList<>();
        violations = new HashMap<>();

        positionProcessor = new PositionProcessor(getPlayerData());
        rotationProcessor = new RotationProcessor(getPlayerData());
        collisionProcessor = new CollisionProcessor(getPlayerData());

        initializeChecks();

        if(player.hasPermission("oneesan.alerts")) alerts = true;
    }

    void initializeChecks() {
        checks.add(new PacketA());
        checks.add(new FlightA());
    }

    public int getViolation(Check check) {
        return violations.getOrDefault(check, 0);
    }

    public void resetViolations() {
        violations.clear();
    }

    public int getViolations() {
        return violations.isEmpty() ? 0 : violations.values().stream().mapToInt(value -> value).sum();
    }

    public void addViolation(Check check) {
        violations.put(check, violations.getOrDefault(check, 0) + 1);
    }
}