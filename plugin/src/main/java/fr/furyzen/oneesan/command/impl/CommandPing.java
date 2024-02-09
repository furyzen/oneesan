package fr.furyzen.oneesan.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import fr.furyzen.oneesan.command.AbstractCommand;
import fr.furyzen.oneesan.command.annotation.CommandMetadata;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

@CommandMetadata(
        name = "ping",
        description = "ping some unknown entity, will it respond..?",
        aliases = { "p", "pong" }
)
public class CommandPing extends AbstractCommand {

    @Override
    public void build(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.executes(context -> {
            context.getSource().getBukkitSender().sendMessage("pong!");
            return SINGLE_SUCCESS;
        });
    }
}