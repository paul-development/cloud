package de.paul.console.setup.interfaces;

import de.paul.console.setup.SetupBuilder;
import de.paul.console.setup.abstracts.SetupEnd;
import de.paul.console.setup.abstracts.SetupInput;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public abstract class ISetup {

    private SetupInput[] setupInputs;
    private SetupInput currentInput;
    private SetupEnd setupEnd;

    public SetupEnd getSetupEnd() {
        return setupEnd;
    }

    public void setSetupEnd(SetupEnd setupEnd) {
        this.setupEnd = setupEnd;
    }

    public SetupInput[] getSetupInputs() {
        return setupInputs;
    }

    public void setSetupInputs(SetupInput... setupInputs) {
        this.setupInputs = setupInputs;
        this.currentInput = setupInputs[0];

        new SetupBuilder(this, setupEnd, setupInputs);
    }

    public SetupInput getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(SetupInput currentInput) {
        this.currentInput = currentInput;
    }
}
