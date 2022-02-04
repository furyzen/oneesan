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

    final Player player;
    final PlayerData playerData;

    private final PositionProcessor positionProcessor;
    private final RotationProcessor rotationProcessor;
    private final CollisionProcessor collisionProcessor;

    private final Map<Check, Integer> violations;
    private final List<Check> checks;
    public boolean alerts;

    public User(Player player) {
        this.player = player;
        this.playerData = new PlayerData(player);

        checks = new ArrayList<>();
        violations = new HashMap<>();

        positionProcessor = new PositionProcessor(getPlayerData());
        this.rotationProcessor = new RotationProcessor(getPlayerData());
        this.collisionProcessor = new CollisionProcessor(getPlayerData());

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

    public void resetViolation() {
        violations.clear();
    }

    public int getViolations() {
        if(violations.isEmpty()) return 0;
        return violations.values().stream().mapToInt(value -> value).sum();
    }

    public void addViolation(Check check) {
        violations.put(check, violations.getOrDefault(check, 0) + 1);
    }
}