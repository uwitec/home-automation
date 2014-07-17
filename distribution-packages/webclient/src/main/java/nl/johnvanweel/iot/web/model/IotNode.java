package nl.johnvanweel.iot.web.model;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;

/**
 * Represents a device on the network
 */
public class IotNode {
    private String name;
    private Capability[] capabilities;

    public IotNode(final String name, final Capability... capabilities) {
        this.name = name;
        this.capabilities = capabilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Capability[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capability[] capabilities) {
        this.capabilities = capabilities;
    }
}
