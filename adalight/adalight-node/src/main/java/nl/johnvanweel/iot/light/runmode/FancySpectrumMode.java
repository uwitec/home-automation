package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.runmode.step.FancySpectrum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Runmode for the 'fancy' spectrum changer
 */
public class FancySpectrumMode extends nl.johnvanweel.iot.light.runmode.RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.SPECTRUM_2.getName();

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
}
