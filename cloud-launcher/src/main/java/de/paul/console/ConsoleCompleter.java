package de.paul.console;

import jdk.internal.org.jline.reader.Candidate;
import jdk.internal.org.jline.reader.Completer;
import jdk.internal.org.jline.reader.LineReader;
import jdk.internal.org.jline.reader.ParsedLine;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class ConsoleCompleter implements Completer {
    private List<String> suggestions;

    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        if (suggestions == null) {
            return;
        }
        if (suggestions.isEmpty()) {
            return;
        }
        candidates.addAll(getCurrentSuggestions());
    }

    public List<Candidate> getCurrentSuggestions() {
        List<Candidate> candidates = new ArrayList<>();
        for (String string : suggestions) {
            candidates.add(new Candidate(string));
        }
        return candidates;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
