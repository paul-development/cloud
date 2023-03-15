package de.paul.console.command;

import de.paul.command.Command;
import de.paul.command.ICommandHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class CommandManager {

    private final Map<Command, ICommandHandler> commandHandlers;

    public CommandManager() {
        this.commandHandlers = new HashMap<>();
    }

    @SafeVarargs
    public final void register(Class<? extends ICommandHandler>... command) {
        try {
            for (Class<? extends ICommandHandler> aClass : command) {
                commandHandlers.put(aClass.getAnnotation(Command.class), aClass.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ICommandHandler getCommandHandlerByName(String command) {
        return commandHandlers.get(commandHandlers.keySet().stream().filter(command1 -> command1.name().equalsIgnoreCase(command)).findAny().orElse(null));
    }

    public Map<Command, ICommandHandler> getCommandHandlers() {
        return commandHandlers;
    }
}
