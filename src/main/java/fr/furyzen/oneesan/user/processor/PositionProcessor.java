package fr.furyzen.oneesan.user.processor;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import fr.furyzen.oneesan.user.data.PlayerData;
import lombok.Data;

@Data
public class PositionProcessor {

    private final PlayerData playerData;

    public void handle(WrapperPlayClientPlayerFlying wrapper) {
        playerData.setOnGroundClient(wrapper.isOnGround());

        playerData.setOnGroundServer(playerData.getY() % (1 / 64D) <= 0.001);
    }

    public void handle(WrapperPlayClientPlayerPosition wrapper) {
        playerData.setLastX(playerData.getX());
        playerData.setLastY(playerData.getY());
        playerData.setLastZ(playerData.getZ());

        playerData.setX(wrapper.getPosition().getX());
        playerData.setY(wrapper.getPosition().getY());
        playerData.setZ(wrapper.getPosition().getZ());

        playerData.setLastMotionX(playerData.getMotionX());
        playerData.setLastMotionY(playerData.getMotionY());
        playerData.setLastMotionZ(playerData.getMotionZ());

        playerData.setMotionX(playerData.getLastX() - playerData.getX());
        playerData.setMotionY(playerData.getLastY() - playerData.getY());
        playerData.setMotionZ(playerData.getLastZ() - playerData.getZ());

        playerData.setOnGroundClient(wrapper.isOnGround());

        playerData.setOnGroundServer(playerData.getY() % (1 / 64D) <= 0.001);
    }

    public void handle(WrapperPlayClientPlayerPositionAndRotation wrapper) {
        playerData.setLastX(playerData.getX());
        playerData.setLastY(playerData.getY());
        playerData.setLastZ(playerData.getZ());

        playerData.setX(wrapper.getPosition().getX());
        playerData.setY(wrapper.getPosition().getY());
        playerData.setZ(wrapper.getPosition().getZ());

        playerData.setLastMotionX(playerData.getMotionX());
        playerData.setLastMotionY(playerData.getMotionY());
        playerData.setLastMotionZ(playerData.getMotionZ());

        playerData.setMotionX(playerData.getLastX() - playerData.getX());
        playerData.setMotionY(playerData.getLastY() - playerData.getY());
        playerData.setMotionZ(playerData.getLastZ() - playerData.getZ());

        playerData.setOnGroundClient(wrapper.isOnGround());

        playerData.setOnGroundServer(playerData.getY() % (1 / 64D) <= 0.001);
    }

    public void handle(WrapperPlayClientPlayerRotation wrapper) {
        playerData.setOnGroundClient(wrapper.isOnGround());

        playerData.setOnGroundServer(playerData.getY() % (1 / 64D) <= 0.001);
    }
}
