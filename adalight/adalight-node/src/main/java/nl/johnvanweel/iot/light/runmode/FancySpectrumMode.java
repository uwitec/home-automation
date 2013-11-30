package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;
import nl.johnvanweel.iot.light.step.FancySpectrum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Runmode for the 'fancy' spectrum changer
 */
public class FancySpectrumMode extends RunMode {
    public static final String RUNMODE = RunModes.SPECTRUM_2.getName();

    private final FancySpectrum spectrum;

    @Autowired
    public FancySpectrumMode(FancySpectrum spectrum) {
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
