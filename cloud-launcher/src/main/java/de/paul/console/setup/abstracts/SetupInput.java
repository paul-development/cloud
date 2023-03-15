package de.paul.console.setup.abstracts;

import java.util.List;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public abstract class SetupInput {

    private final String question;

    protected SetupInput(String question) {
        this.question = question;
    }

    public abstract List<String> getSuggestions();

    public abstract boolean handle(String input);

    public String getQuestion() {
        return question;
    }
}

