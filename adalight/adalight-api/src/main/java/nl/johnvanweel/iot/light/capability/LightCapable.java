package nl.johnvanweel.iot.light.capability;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;

/**
 * Device is capable of displaying light
 */
public class LightCapable extends Capability {

    public LightCapable() {
        super("Lights", "Control lights");
    }
}
