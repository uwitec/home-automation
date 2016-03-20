package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays all colors
 */
public class OffMode extends RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.OFF.getName();

    private final ILightService lightService;

    @Autowired
    public OffMode(ILightService lightService) {
        this.lightService = lightService;
    }

    @Override
    protected void executeStep() {
        lightService.allPixels(0,0,0);
    }

    public LightRunMode identify() {
        return new LightRunMode(RUNMODE);
    }
}
