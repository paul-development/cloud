package de.paul.console.sender;

import de.paul.CloudLauncher;
import de.paul.console.ICommandSender;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class ConsoleSender implements ICommandSender {
    @Override
    public void sendMessage(String message) {
        CloudLauncher.getInstance().getCloudLogger().info(message);
    }
}
