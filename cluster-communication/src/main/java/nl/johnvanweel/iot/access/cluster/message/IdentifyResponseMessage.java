package nl.johnvanweel.iot.access.cluster.message;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;

/**
 * Response to Identification request.
 */
public class IdentifyResponseMessage extends GroupMessage {
    private String host;
    private Capability[] capabilities;

    public IdentifyResponseMessage(String host, Capability... capabilities) {
        this.host = host;
        this.capabilities = capabilities;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Capability[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capability[] capabilities) {
        this.capabilities = capabilities;
    }
}
