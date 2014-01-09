package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;
import nl.johnvanweel.iot.light.step.Sunset;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class SunsetMode extends RunMode {
    public static final String RUNMODE = RunModes.SUNSET.getName();

    private final Sunset sunset;

    @Autowired
    public SunsetMode(Sunset sunset) {
        this.sunset = sunset;
    }

    @Override
    protected void executeStep() {
        sunset.step();
    }

    @Override
    public String identify() {
        return RUNMODE;
    }

    @Override
    protected void reconfigure(IlluminationGroupMessage message) {

    }
}
