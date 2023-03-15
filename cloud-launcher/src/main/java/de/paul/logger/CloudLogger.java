package de.paul.logger;

import de.paul.CloudLauncher;
import de.paul.console.Color;
import de.paul.console.LogType;
import jdk.internal.org.jline.reader.LineReader;
import jdk.internal.org.jline.utils.InfoCmp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class CloudLogger extends Logger implements ICloudLogger {
    private final CopyOnWriteArrayList<String> cachedMessages = new CopyOnWriteArrayList<>();

    private final File logDir = new File("logs/");

    public CloudLogger() {
        super("Cloud", null);
    }

    public void write(String prefix, String message) {
        Date date = new Date();
        String format = Color.toColoredString("§r[" + new SimpleDateFormat("HH:mm:ss").format(date) + " | " + Color.CYAN.getColor() + prefix + Color.RESET.getColor() + "]" + Color.RESET.getColor() + " §r»§r " + message);
        LineReader lineReader = CloudLauncher.getInstance().getConsoleManager().getLineReader();
        lineReader.getTerminal().puts(InfoCmp.Capability.carriage_return);
        lineReader.getTerminal().writer().println(format);
        lineReader.getTerminal().flush();
        if (lineReader.isReading()) {
            lineReader.callWidget(LineReader.REDRAW_LINE);
            lineReader.callWidget(LineReader.REDISPLAY);
        }
        cachedMessages.add("[" + new SimpleDateFormat("HH:mm:ss").format(date) + "] " + prefix + ": " + message);
    }

    private void write(LogType logType, String message) {
        Date date = new Date();
        String format = Color.toColoredString("§r[" + new SimpleDateFormat("HH:mm:ss").format(date) + " | " + logType.getColor().getColor() + logType.getDisplay() + Color.RESET.getColor() + "]" + Color.RESET.getColor() + " §r»§r " + message);

        LineReader lineReader = CloudLauncher.getInstance().getConsoleManager().getLineReader();
        lineReader.getTerminal().puts(InfoCmp.Capability.carriage_return);
        lineReader.getTerminal().writer().println(format);
        lineReader.getTerminal().flush();

        if (lineReader.isReading()) {
            lineReader.callWidget(LineReader.REDRAW_LINE);
            lineReader.callWidget(LineReader.REDISPLAY);
        }

        cachedMessages.add("[" + new SimpleDateFormat("HH:mm:ss").format(date) + "] " + logType.getDisplay() + ": " + message);
    }


    private boolean isOlder(File file) {
        long millis = TimeUnit.DAYS.toMillis(10);
        return (System.currentTimeMillis() - file.lastModified()) > millis;
    }

    @Override
    public void info(String msg) {
        super.info(msg);
        write(LogType.CONSOLE, msg);
    }

    @Override
    public void warning(String msg) {
        super.warning(msg);
        write(LogType.WARNING, msg);
    }

    @Override
    public void severe(String msg) {
        super.severe(msg);
        write(LogType.ERROR, msg);
    }

    public void setup(String msg) {
        super.info(msg);
        write(LogType.SETUP, msg);
    }

    public void success(String message) {
        super.info(message);
        write(LogType.SUCCESS, message);
    }

    public void debug(String msg) {
        super.info(msg);
        write(LogType.DEBUG, msg);
    }

    public void deleteOldLogs() {
        File[] files = logDir.listFiles();
        if (files == null) {
            return;
        }
        Arrays.stream(files).forEach(file -> {
            if (isOlder(file)) {
                file.delete();
            }
        });
    }

    public void clear() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    public CopyOnWriteArrayList<String> getCachedMessages() {
        return cachedMessages;
    }

}
