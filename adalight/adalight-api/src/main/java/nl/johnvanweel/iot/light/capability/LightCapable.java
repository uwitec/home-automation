package nl.johnvanweel.iot.light.capability;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunMode;

/**
 * Device is capable of displaying light
 */
public class LightCapable  {
    private RunMode[] runModeses;

    public LightCapable() {
//        super("Lights", "Control lights");

        runModeses = RunMode.values();
    }

    public RunMode[] getRunModeses() {
        return runModeses;
    }

    public void setRunModeses(RunMode[] runModeses) {
        this.runModeses = runModeses;
    }
}
