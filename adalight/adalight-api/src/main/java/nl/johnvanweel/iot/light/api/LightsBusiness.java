package nl.johnvanweel.iot.light.api;

import nl.johnvanweel.iot.access.cluster.hazelcast.ClusterDataMapAccess;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.LightChangeCommand;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Business component for controlling lights
 */
public class LightsBusiness {
	private final ClusterDataMapAccess<LightChangeCommand> runModeCluster;

	@Autowired
	public LightsBusiness(@Qualifier("lightsRunModeDataAccess") ClusterDataMapAccess<LightChangeCommand> runModeCluster) {
		this.runModeCluster = runModeCluster;
	}

	/**
	 * Changes the runmode
	 * @param newMode new mode
	 */
	public void changeMode(String newMode) {
		runModeCluster.store(new LightChangeCommand(RunMode.valueOf(newMode), null));
	}

	/**
	 * Changes the color
	 * @param rgb rgb values of the new color
	 */
	public void changeColor(int[] rgb) {
		runModeCluster.store(new LightChangeCommand(null, rgb));
	}

	/**
	 * Create a listener for light change commands
	 * @param listener listener
	 */
	public void addListener(DefaultEntryListener<String, LightChangeCommand> listener) {
		runModeCluster.registerListener(listener);
	}
}
