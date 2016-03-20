package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.light.runmode.step.Spectrum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays all colors
 */
public class SpectrumMode extends nl.johnvanweel.iot.light.runmode.RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.SPECTRUM.getName();

    private final Spectrum spectrum;

    @Autowired
    public SpectrumMode(Spectrum spectrum) {
        this.spectrum = spectrum;
    }

    @Override
    protected void executeStep() {
        spectrum.step();
    }

    @Override
    public void toggleFilter(String name) {
        // No filters
    }

    public LightRunMode identify() {
        return new LightRunMode(RUNMODE);
    }
}
