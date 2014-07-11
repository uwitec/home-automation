package nl.johnvanweel.iot.sensornetwork;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;

/**
 * User: jvweel
 * Date: 7/11/14
 * Time: 6:39 PM
 */
public class SensorCapability extends Capability {
	public SensorCapability() {
		super("Sensitive", "Reads sensor data");
	}
}
