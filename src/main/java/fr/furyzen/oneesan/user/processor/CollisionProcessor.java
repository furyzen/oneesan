package fr.furyzen.oneesan.user.processor;

import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import fr.furyzen.oneesan.user.data.PlayerData;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

@Data
public class CollisionProcessor {

    private final PlayerData data;
    private final List<Block> collidingBlocks = new ArrayList<>();

    //TODO MORE
    private boolean liquid, ice, web, climbable, collisionOnGround;

    public void handle(WrapperPlayClientPlayerPosition wrapper) {

        this.collidingBlocks.clear();

        final Vector3d pos = wrapper.getPosition();
        final Location location = new Location(data.getPlayer().getWorld(), pos.x, pos.y, pos.z);

        final double distanceToGround = 0.3D;

        for (double locX = location.getX(); locX <= distanceToGround; locX += 0.03125f) {
            for (double locZ = location.getZ(); locZ <= distanceToGround; locZ += 0.03125f) {
                collidingBlocks.add(new Location(data.getPlayer().getWorld(), locX, -0.005, locZ).getBlock());

            }


        }

        update();


    }


    public void handle(WrapperPlayClientPlayerPositionAndRotation wrapper) {

        this.collidingBlocks.clear();

        final Vector3d pos = wrapper.getPosition();
        final Location location = new Location(data.getPlayer().getWorld(), pos.x, pos.y, pos.z);

        final double distanceToGround = 0.3D;

        for (double locX = location.getX(); locX <= distanceToGround; locX += 0.03125f) {
            for (double locZ = location.getZ(); locZ <= distanceToGround; locZ += 0.03125f) {
                collidingBlocks.add(new Location(data.getPlayer().getWorld(), locX, -0.005, locZ).getBlock());

            }


        }

        update();

    }

    private void update() {
        this.collisionOnGround = collidingBlocks.stream().anyMatch(block -> block.getType() != Material.AIR);
        this.liquid = collidingBlocks.stream().anyMatch(Block::isLiquid);
        this.web = collidingBlocks.stream().anyMatch(block -> block.getType() == Material.WEB);
        this.climbable = collidingBlocks.stream().anyMatch(block -> block.getType() == Material.VINE || block.getType() == Material.LADDER);
        this.ice = collidingBlocks.stream().anyMatch(block -> block.getType() == Material.ICE || block.getType() == Material.PACKED_ICE);

    }

}
