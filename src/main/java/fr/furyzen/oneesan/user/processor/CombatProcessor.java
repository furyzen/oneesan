package fr.furyzen.oneesan.user.processor;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAnimation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import fr.furyzen.oneesan.user.data.PlayerData;
import lombok.Data;

@Data
public class CombatProcessor {

    private final PlayerData data;

    public void handle(WrapperPlayClientInteractEntity wrapper) {
        if(wrapper.getAction().equals(WrapperPlayClientInteractEntity.InteractAction.ATTACK)) {
            data.setLastAttack(System.currentTimeMillis());
        }
    }

    public void handle() {
        data.setLastSwing(System.currentTimeMillis());
    }
}
