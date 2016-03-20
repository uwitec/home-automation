package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.light.runmode.step.Cylon;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays all colors
 */
public class CylonMode extends nl.johnvanweel.iot.light.runmode.RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.CYLON.getName();

    private final Cylon spectrum;

    @Autowired
    public CylonMode(Cylon cylon) {
        this.spectrum = cylon;
    }

    @Override
    protected void executeStep() {
        spectrum.step();
    }

    public LightRunMode identify() {
        return new LightRunMode(RUNMODE);
    }
}
