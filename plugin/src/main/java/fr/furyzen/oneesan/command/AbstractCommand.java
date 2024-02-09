package fr.furyzen.oneesan.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import fr.furyzen.oneesan.Oneesan;
import fr.furyzen.oneesan.command.annotation.CommandMetadata;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.commands.CommandSourceStack;

import java.util.Arrays;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.color;

public abstract class AbstractCommand {
    private final CommandMetadata commandMetadata = getClass().getAnnotation(CommandMetadata.class);

    private final String commandName = commandMetadata.name();

    private final String commandDescription = commandMetadata.description();

    private final String[] commandAliases = commandMetadata.aliases();


    public abstract void build(LiteralArgumentBuilder<CommandSourceStack> builder);

    public String getCommandName() {
        return commandName;
    }
    public String getCommandDescription() {
        return commandDescription;
    }

    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        register(commandDispatcher, commandName);
        Arrays.stream(commandAliases).forEach(alias -> register(commandDispatcher, alias));
    }

    private void register(CommandDispatcher<CommandSourceStack> commandDispatcher, String name) {
        var builder = literal(name);
        build(builder);
        commandDispatcher.register(builder);
    }

    protected <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(String commandName, ArgumentType<T> argumentType) {
        return RequiredArgumentBuilder.argument(commandName, argumentType);
    }

    protected LiteralArgumentBuilder<CommandSourceStack> literal(String commandName) {
        return LiteralArgumentBuilder.literal(commandName);
    }

    protected Oneesan oneesan = Oneesan.INSTANCE;
}
