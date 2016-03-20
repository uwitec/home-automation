package nl.johnvanweel.iot.light;

import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;
import nl.johnvanweel.iot.light.api.RunMode;

import java.util.UUID;

/**
 * Command object to change the lights behavior
 */
public class LightChangeCommand implements IStorable{
	private final String runMode;

	public LightChangeCommand(String runMode) {
		this.runMode = runMode;
	}

	public String getRunMode() {
		return runMode;
	}

	@Override
	public String getKey() {
		return UUID.randomUUID().toString();
	}
}
