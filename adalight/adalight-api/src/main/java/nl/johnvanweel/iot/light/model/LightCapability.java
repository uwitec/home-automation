package nl.johnvanweel.iot.light.model;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;

/**
 * Created by john on 3/20/16.
 */
public class LightCapability extends Capability {
    private LightRunMode[] lightRunModes;

    protected LightCapability(String name, String description) {
        super(name, description);
    }

    public LightCapability(String name, String description, LightRunMode[] runModes) {
        super(name, description);

        this.lightRunModes = runModes;
    }

    public LightRunMode[] getLightRunModes() {
        return lightRunModes;
    }
}
