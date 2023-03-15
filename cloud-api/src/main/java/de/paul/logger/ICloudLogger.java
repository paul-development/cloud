package de.paul.logger;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public interface ICloudLogger {

    void info(String message);

    void servere(String message);

    void warning(String message);

    void success(String message);
}
