package nl.johnvanweel.iot.access.cluster.capabilities;

import java.io.Serializable;

/**
 * Defines a capability of a device.
 * TODO: Not too happy with this solution! Only used for user-interface to display and act on device capabilities.
 * The idea is to tie certain Group Messages to these capabilities.
 */
public final class Capability implements Serializable {
    private final String name;
    private final String description;


    protected Capability(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
