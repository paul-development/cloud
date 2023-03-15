package de.paul.console;

import de.paul.CloudLauncher;
import de.paul.command.ICommandHandler;
import jdk.internal.org.jline.reader.LineReader;
import jdk.internal.org.jline.reader.LineReaderBuilder;
import jdk.internal.org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class ConsoleManager {

    private final LineReader lineReader;
    private final ConsoleCompleter consoleCompleter;
    private final String prompt =
            System.getProperty("user.name") + "@" + Color.CYAN + "cloud-v1.0.0" + " $ ";
    private Thread thread;

    public ConsoleManager() {
        consoleCompleter = new ConsoleCompleter();
        lineReader = createLineReader();
    }

    private LineReader createLineReader() {
        try (Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .streams(System.in, System.out)
                .encoding(Charset.forName("UTF-8"))
                .build()) {
            return LineReaderBuilder.builder()
                    .completer(consoleCompleter)
                    .terminal((jdk.internal.org.jline.terminal.Terminal) terminal)
                    .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
                    .build();
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void startThread() {
        thread = new Thread(() -> {
            try {
                String line;
                while (!Thread.currentThread().isInterrupted()) {
                    line = lineReader.readLine(prompt);
                    handleInput(line);
                }
            } catch (UserInterruptException ignored) {
            }
        });
        thread.start();
    }

    public void handleInput(String input) {

       /* if (SetupBuilder.getCurrentSetup() != null) {
            SetupBuilder.nextQuestion(SetupBuilder.getCurrentSetup().getCurrentInput().handle(input));
            return;
        }*/

        if (input.isEmpty()) {
            return;
        }

        String[] args = input.split(" ");
        String command = args[0];
        if (CloudLauncher.getInstance().getCommandManager().getCommandHandlerByName(command) == null) {
            CloudLauncher.getInstance().getCloudLogger().warning("The command could not be found! Please type 'help' for help.");
            return;
        }
        ICommandHandler commandHandler = CloudLauncher.getInstance().getCommandManager().getCommandHandlerByName(command);
        commandHandler.handle(CloudLauncher.getInstance().getConsoleSender(), args);
    }

    public void stopThread() {
        lineReader.getTerminal().reader().shutdown();
        lineReader.getTerminal().pause();
        thread.interrupt();
    }

    public LineReader getLineReader() {
        return lineReader;
    }

    public ConsoleCompleter getConsoleCompleter() {
        return consoleCompleter;
    }

    public String getPrefix() {
        return prompt;
    }

}
