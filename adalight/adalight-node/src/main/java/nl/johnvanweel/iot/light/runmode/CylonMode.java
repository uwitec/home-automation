package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;
import nl.johnvanweel.iot.light.step.Cylon;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays all colors
 */
public class CylonMode extends RunMode {
    public static final String RUNMODE = RunModes.CYLON.getName();

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
    protected void reconfigure(IlluminationGroupMessage message) {

    }
}
