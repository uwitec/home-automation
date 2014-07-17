package nl.johnvanweel.iot.light.access.cluster;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.LightChangeCommand;
import nl.johnvanweel.iot.light.api.LightsBusiness;
import nl.johnvanweel.iot.light.runmode.RunModeControl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Listens to IlluminationGroupMessage. This will read the new light value from the message and
 * set it.
 */
public class RunmodeListener implements DefaultEntryListener<String, LightChangeCommand> {
	private Logger log = Logger.getLogger(RunmodeListener.class);

	private final RunModeControl control;
	private final LightsBusiness business;

	@Autowired
	public RunmodeListener(final RunModeControl runmodeControl, LightsBusiness business) {
		this.control = runmodeControl;
		this.business = business;
	}

	@PostConstruct
	public void addListener() {
		business.addListener(this);
	}

	@Override
	public void entryAdded(EntryEvent<String, LightChangeCommand> event) {
		log.info("Received command " + event);

		if (event.getValue().getRunMode() != null) {
			control.start(event.getValue().getRunMode().toString());
		}
	}
}
