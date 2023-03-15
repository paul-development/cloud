package de.paul.command;

import de.paul.console.ICommandSender;
import jdk.internal.org.jline.reader.Candidate;

import java.util.List;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public interface ICommandHandler {

    void handle(ICommandSender iCommandSender, String[] args);

    List<Candidate> getSuggestions();

}
