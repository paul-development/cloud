package de.paul.console.setup;

import de.paul.console.setup.abstracts.SetupEnd;
import de.paul.console.setup.abstracts.SetupInput;
import de.paul.console.setup.interfaces.ISetup;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class SetupBuilder {

    private static final List<ISetup> setupQueue = new ArrayList<>();

    private static SetupInput currentInput;
    private static SetupEnd setupEnd;
    private static SetupInput[] setupInputs;
    private static int currentIndex = 0;
    private static ISetup currentSetup;

    public SetupBuilder(ISetup setup, SetupEnd setupEnd, SetupInput... setupInputs) {
        currentSetup = setup;
        setupQueue.add(currentSetup);
        SetupBuilder.setupEnd = setupEnd;
        SetupBuilder.setupInputs = setupInputs;
        currentInput = setupInputs[currentIndex];
        //CloudLauncher.getInstance().getConsoleManager().getConsoleCompleter().setSuggestions(currentInput.getSuggestions());
        //CloudLauncher.getInstance().getCloudLogger().setup(currentInput.getQuestion());
    }

}
