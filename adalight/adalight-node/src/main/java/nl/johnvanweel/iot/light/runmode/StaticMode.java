package nl.johnvanweel.iot.light.runmode;

import nl.johnvanweel.iot.light.runmode.step.Static;
import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Only accepts reconfiguration messages, resulting in a non-changing light.
 */
public class StaticMode extends nl.johnvanweel.iot.light.runmode.RunMode {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.STATIC.getName();

    private final Static aStatic;

    @Autowired
    public StaticMode(final Static aStatic) {
        this.aStatic = aStatic;
    }

    @Override
    protected void executeStep() {
        aStatic.step();
    }

    @Override
    public String identify() {
        return RUNMODE;
    }
}
