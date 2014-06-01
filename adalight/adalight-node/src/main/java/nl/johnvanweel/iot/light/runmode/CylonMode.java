package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;
import nl.johnvanweel.iot.light.step.Spectrum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays all colors
 */
public class CylonMode extends RunMode {
    public static final String RUNMODE = RunModes.SPECTRUM.getName();

    private final Spectrum spectrum;

    @Autowired
    public CylonMode(Spectrum spectrum) {
        this.spectrum = spectrum;
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
