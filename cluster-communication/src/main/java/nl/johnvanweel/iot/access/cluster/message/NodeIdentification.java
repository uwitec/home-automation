package nl.johnvanweel.iot.access.cluster.message;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;
import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;

/**
 * Response to Identification request.
 */
public class NodeIdentification implements IStorable {
    private String host;
    private Capability[] capabilities;

    public NodeIdentification(String host, Capability... capabilities) {
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

	@Override
	public String getKey() {
		return host;
	}
}
