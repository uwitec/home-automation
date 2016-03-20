package nl.johnvanweel.iot.light.api;

import nl.johnvanweel.iot.access.cluster.hazelcast.ClusterDataMapAccess;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.LightChangeCommand;
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
		runModeCluster.store(new LightChangeCommand(newMode));
	}


	/**
	 * Create a listener for light change commands
	 * @param listener listener
	 */
	public void addListener(DefaultEntryListener<String, LightChangeCommand> listener) {
		runModeCluster.registerListener(listener);
	}
}
