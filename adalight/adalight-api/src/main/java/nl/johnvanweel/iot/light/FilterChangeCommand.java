package nl.johnvanweel.iot.light;

import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;

import java.util.UUID;

/**
 * Command object to change the lights behavior
 */
public class FilterChangeCommand implements IStorable{
	private final String runMode;
	private final String filter;

	public FilterChangeCommand(String runMode, String filter) {
		this.runMode = runMode;
		this.filter = filter;
	}

	public String getRunMode() {
		return runMode;
	}

	public String getFilter() {
		return filter;
	}

	@Override
	public String getKey() {
		return UUID.randomUUID().toString();
	}
}
