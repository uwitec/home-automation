package nl.johnvanweel.iot.light;

import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunMode;

import java.util.UUID;

/**
 * Command object to change the lights behavior
 */
public class LightChangeCommand implements IStorable{
	private final RunMode runMode;
	private final int[] rgb;


	public LightChangeCommand(RunMode runMode, int[] rgb) {
		this.runMode = runMode;
		this.rgb = rgb;
	}

	public RunMode getRunMode() {
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
