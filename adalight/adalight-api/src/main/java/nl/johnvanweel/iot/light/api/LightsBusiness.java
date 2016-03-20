package nl.johnvanweel.iot.light.api;

import nl.johnvanweel.iot.access.cluster.hazelcast.ClusterDataMapAccess;
import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.FilterChangeCommand;
import nl.johnvanweel.iot.light.LightChangeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Business component for controlling lights
 */
public class LightsBusiness {
	private final ClusterDataMapAccess<IStorable> runModeCluster;

	@Autowired
	public LightsBusiness(@Qualifier("lightsRunModeDataAccess") ClusterDataMapAccess<IStorable> runModeCluster) {
		this.runModeCluster = runModeCluster;
	}

	/**
	 * Changes the runmode
	 * @param newMode new mode
	 */
	public void changeMode(String newMode) {
		runModeCluster.store(new LightChangeCommand(newMode));
	}


	public void toggleFilter(String mode, String filter) {
		runModeCluster.store(new FilterChangeCommand(mode, filter));
	}

	/**
	 * Create a listener for light change commands
	 * @param listener listener
	 */
	public void addListener(DefaultEntryListener listener) {
		runModeCluster.registerListener(listener);
	}

}
