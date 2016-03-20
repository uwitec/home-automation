package nl.johnvanweel.iot.light;

import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;
import nl.johnvanweel.iot.light.api.RunMode;

import java.util.UUID;

/**
 * Command object to change the lights behavior
 */
public class LightChangeCommand implements IStorable{
	private final String runMode;
	private final int[] rgb;


	public LightChangeCommand(String runMode, int[] rgb) {
		this.runMode = runMode;
		this.rgb = rgb;
	}

	public String getRunMode() {
		return runMode;
	}

	public int[] getRgb() {
		return rgb;
	}

	@Override
	public String getKey() {
		return UUID.randomUUID().toString();
	}
}
