package nl.johnvanweel.iot.light.capability;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;

/**
 * Device is capable of displaying light
 */
public class LightCapable extends Capability {
    private RunModes[] runModeses;

    public LightCapable() {
        super("Lights", "Control lights");

        runModeses = RunModes.values();
    }

    public RunModes[] getRunModeses() {
        return runModeses;
    }

    public void setRunModeses(RunModes[] runModeses) {
        this.runModeses = runModeses;
    }
}
