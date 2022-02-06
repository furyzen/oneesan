package fr.furyzen.oneesan.user.processor;

import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import fr.furyzen.oneesan.user.data.PlayerData;
import lombok.Data;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

@Data
public class CollisionProcessor {
    //TODO: rewrite that

    private final PlayerData data;
    private final List<Block> collidingBlocks = new ArrayList<>();


    public void handle(WrapperPlayClientPlayerFlying wrapper) {
        final Vector3d pos = new Vector3d(data.getX(), data.getY(), data.getZ());

        final double offsetXZ = 0.3D + 0.125D;
        final double offsetY = 0.5001D;

        for (double locX = pos.x - offsetXZ; locX <= pos.x + offsetXZ; locX += 0.03125f) {
            for (double locY = pos.y - offsetY; locY <= pos.y + (1.8D + offsetY); locY += 0.03125f) {
                for (double locZ = pos.z - offsetXZ; locZ <= pos.z + offsetXZ; locZ += 0.03125f) {
                    final Block block = new Location(data.getPlayer().getWorld(), locX, locY, locZ).getBlock();
                    if(!collidingBlocks.contains(block)) {
                        collidingBlocks.add(block);
                    }
                }
            }
        }
        update();
    }
    public void handle(WrapperPlayClientPlayerPosition wrapper) {
        final Vector3d pos = new Vector3d(data.getX(), data.getY(), data.getZ());

        final double offsetXZ = 0.3D + 0.125D;
        final double offsetY = 0.5001D;

        for (double locX = pos.x - offsetXZ; locX <= pos.x + offsetXZ; locX += 0.03125f) {
            for (double locY = pos.y - offsetY; locY <= pos.y + (1.8D + offsetY); locY += 0.03125f) {
                for (double locZ = pos.z - offsetXZ; locZ <= pos.z + offsetXZ; locZ += 0.03125f) {
                    final Block block = new Location(data.getPlayer().getWorld(), locX, locY, locZ).getBlock();
                    if(!collidingBlocks.contains(block)) {
                        collidingBlocks.add(block);
                    }
                }
            }
        }
        update();
    }

    public void handle(WrapperPlayClientPlayerPositionAndRotation wrapper) {
        final Vector3d pos = new Vector3d(data.getX(), data.getY(), data.getZ());

        final double offsetXZ = 0.3D + 0.125D;
        final double offsetY = 0.5001D;

        for (double locX = pos.x - offsetXZ; locX <= pos.x + offsetXZ; locX += 0.03125f) {
            for (double locY = pos.y - offsetY; locY <= pos.y + (1.8D + offsetY); locY += 0.03125f) {
                for (double locZ = pos.z - offsetXZ; locZ <= pos.z + offsetXZ; locZ += 0.03125f) {
                    final Block block = new Location(data.getPlayer().getWorld(), locX, locY, locZ).getBlock();
                    if(!collidingBlocks.contains(block)) {
                        collidingBlocks.add(block);
                    }
                }
            }
        }
        update();
    }

    private void update() {
        data.setCollidingGround(collidingBlocks.stream().anyMatch(block -> block.getType() != Material.AIR));
        data.setCollidingLiquid(collidingBlocks.stream().anyMatch(Block::isLiquid));
        data.setCollidingWeb(collidingBlocks.stream().anyMatch(block -> block.getType() == Material.WEB));
        data.setCollidingClimbable(collidingBlocks.stream().anyMatch(block -> block.getType() == Material.VINE || block.getType() == Material.LADDER));
        data.setCollidingIce(collidingBlocks.stream().anyMatch(block -> block.getType() == Material.ICE || block.getType() == Material.PACKED_ICE));

        if(data.isOnGroundClient())
            data.setClientAirTicks(0);
        else data.setClientAirTicks(data.getClientAirTicks() + 1);

        if(data.isCollidingGround())
            data.setServerAirTicks(0);
        else data.setServerAirTicks(data.getServerAirTicks() + 1);

        collidingBlocks.clear();
    }

}
