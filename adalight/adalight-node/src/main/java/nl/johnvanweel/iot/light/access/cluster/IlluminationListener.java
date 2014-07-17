package nl.johnvanweel.iot.light.access.cluster;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.access.cluster.listener.IMessageListener;
import nl.johnvanweel.iot.light.LightChangeCommand;
import nl.johnvanweel.iot.light.api.LightsBusiness;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.runmode.RunModeControl;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Listens to IlluminationGroupMessage. This will read the new light value from the message and
 * set it.
 */
public class IlluminationListener implements DefaultEntryListener<String, LightChangeCommand> {
	private final RunModeControl control;
	private final LightsBusiness business;

	@Autowired
	public IlluminationListener(final RunModeControl runmodeControl, LightsBusiness business) {
		this.control = runmodeControl;
		this.business = business;
	}

	@PostConstruct
	public void addListener() {
		business.addListener(this);
	}

	@Override
	public void entryAdded(EntryEvent<String, LightChangeCommand> event) {
		if (event.getValue().getRgb() != null) {
			control.reconfigure(event.getValue().getRgb());
		}
	}
}
