package de.paul;

import de.paul.console.ConsoleManager;
import de.paul.console.command.CommandManager;
import de.paul.console.sender.ConsoleSender;
import de.paul.logger.CloudLogger;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class CloudLauncher {

    private static CloudLauncher instance;
    private final ConsoleManager consoleManager;
    private final CommandManager commandManager;
    private final CloudLogger cloudLogger;
    private final ConsoleSender consoleSender;

    public CloudLauncher() {
        instance = this;

        consoleManager = new ConsoleManager();
        commandManager = new CommandManager();
        cloudLogger = new CloudLogger();

        getCloudLogger().clear();
        getCloudLogger().info("Trying to Starting the cloud...");


        consoleSender = new ConsoleSender();
    }

    public static void main(String[] args) {
        new CloudLauncher();
    }

    public static CloudLauncher getInstance() {
        return instance;
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CloudLogger getCloudLogger() {
        return cloudLogger;
    }

    public ConsoleSender getConsoleSender() {
        return consoleSender;
    }
}