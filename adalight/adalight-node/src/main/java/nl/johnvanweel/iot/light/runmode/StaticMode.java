package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.MyIllumiGM;
import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Only accepts reconfiguration messages, resulting in a non-changing light.
 */
public class StaticMode extends nl.johnvanweel.iot.light.runmode.RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunMode.STATIC.getName();

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
    protected void reconfigure(int[] rgb) {
            lightService.allPixels(rgb[0], rgb[1], rgb[2]);


    }
}
