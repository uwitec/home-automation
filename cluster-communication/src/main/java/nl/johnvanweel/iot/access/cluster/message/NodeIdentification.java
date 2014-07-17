package nl.johnvanweel.iot.access.cluster.message;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;
import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;

import java.util.UUID;

/**
 * Response to Identification request.
 */
public class NodeIdentification implements IStorable {
	private UUID uuid;
	private String host;
	private Capability[] capabilities;

	public NodeIdentification(final UUID uuid, String host, Capability... capabilities) {
		this.uuid = uuid;
		this.host = host;
		this.capabilities = capabilities;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
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
		return uuid.toString();
	}
}
