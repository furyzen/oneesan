package fr.furyzen.oneesan.user.processor;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import fr.furyzen.oneesan.user.data.PlayerData;
import lombok.Data;

@Data
public class RotationProcessor {

    private final PlayerData data;

    public void handle(WrapperPlayClientPlayerRotation wrapper) {

        data.setLastYaw(data.getYaw());
        data.setLastPitch(data.getPitch());

        data.setYaw(wrapper.getYaw());
        data.setPitch(wrapper.getPitch());

        data.setLastDeltaYaw(data.getDeltaYaw());
        data.setLastDeltaPitch(data.getDeltaPitch());

        data.setDeltaYaw((data.getYaw() - data.getLastYaw()) % 360f);
        data.setDeltaPitch(data.getPitch() - data.getLastPitch());

        data.setAccelYaw(data.getDeltaYaw() - data.getLastDeltaYaw());
        data.setAccelPitch(data.getDeltaPitch() - data.getLastDeltaPitch());

    }

    public void handle(WrapperPlayClientPlayerPositionAndRotation wrapper) {

        data.setLastYaw(data.getYaw());
        data.setLastPitch(data.getPitch());

        data.setYaw(wrapper.getYaw());
        data.setPitch(wrapper.getPitch());

        data.setLastDeltaYaw(data.getDeltaYaw());
        data.setLastDeltaPitch(data.getDeltaPitch());

        data.setDeltaYaw((data.getYaw() - data.getLastYaw()) % 360f);
        data.setDeltaPitch(data.getPitch() - data.getLastPitch());

        data.setAccelYaw(data.getDeltaYaw() - data.getLastDeltaYaw());
        data.setAccelPitch(data.getDeltaPitch() - data.getLastDeltaPitch());
    }



}
