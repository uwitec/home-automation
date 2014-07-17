package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.step.Cylon;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays all colors
 */
public class CylonMode extends nl.johnvanweel.iot.light.runmode.RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunMode.CYLON.getName();

    private final Cylon spectrum;

    @Autowired
    public CylonMode(Cylon cylon) {
        this.spectrum = cylon;
    }

    @Override
    protected void executeStep() {
        spectrum.step();
    }

    @Override
    public String identify() {
        return RUNMODE;
    }

    @Override
    protected void reconfigure(int[] message) {

    }
}
