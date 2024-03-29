package fr.furyzen.oneesan.registry;

import com.mojang.brigadier.CommandDispatcher;
import fr.furyzen.oneesan.command.AbstractCommand;
import fr.furyzen.oneesan.command.impl.CommandPing;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class CommandRegistry {

    private JavaPlugin plugin;

    private final CommandDispatcher<CommandSourceStack> commandDispatcher =
            ((CraftServer) Bukkit.getServer()).getServer().resources.managers().commands.getDispatcher();

    private final List<AbstractCommand> commandList = new ArrayList<>();

    public CommandRegistry(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        registerCommand(CommandPing::new);
    }

    public void registerCommand(Supplier<AbstractCommand> supplier) {
        AbstractCommand command = supplier.get();

        commandList.add(command);
        command.register(commandDispatcher);
    }

    public Optional<AbstractCommand> getCommandByName(String commandName) {
        return commandList.stream()
                .filter(command -> command.getCommandName().equals(commandName)
                        || Arrays.asList(command.getCommandAliases()).contains(commandName))
                .findFirst();
    }

    public Optional<AbstractCommand> getCommandByClass(Class<AbstractCommand> commandClass) {
        return commandList.stream()
                .filter(command -> command.getClass() == commandClass)
                .findFirst();
    }

    public List<AbstractCommand> getCommandList() {
        return commandList;
    }
}