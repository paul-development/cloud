package de.paul;

import de.paul.console.ConsoleManager;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class CloudLauncher {

    private static CloudLauncher instance;
    private final ConsoleManager consoleManager;

    public CloudLauncher() {
        instance = this;

        consoleManager = new ConsoleManager();
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
}