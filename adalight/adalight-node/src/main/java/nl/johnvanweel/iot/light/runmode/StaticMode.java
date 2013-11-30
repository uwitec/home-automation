package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;
import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Only accepts reconfiguration messages, resulting in a non-changing light.
 */
public class StaticMode extends RunMode {
    public static final String RUNMODE = RunModes.STATIC.getName();

    private final ILightService lightService;

    @Autowired
    public StaticMode(ILightService lightService) {
        this.lightService = lightService;
    }

    @Override
    protected void executeStep() {

    }

    @Override
    public String identify() {
        return RUNMODE;
    }

    @Override
    protected void reconfigure(IlluminationGroupMessage message) {
        lightService.allPixels(message.getRgb()[0], message.getRgb()[1], message.getRgb()[2]);
    }
}
