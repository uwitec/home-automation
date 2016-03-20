package nl.johnvanweel.iot.light.access.cluster;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.FilterChangeCommand;
import nl.johnvanweel.iot.light.api.LightsBusiness;
import nl.johnvanweel.iot.light.runmode.RunMode;
import nl.johnvanweel.iot.light.runmode.RunModeControl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Listens to IlluminationGroupMessage. This will read the new light value from the message and
 * set it.
 */
public class FilterListener implements DefaultEntryListener<String, FilterChangeCommand> {
    private final RunModeControl control;
    private final LightsBusiness business;
    private final List<RunMode> runModes;
    private Logger log = Logger.getLogger(FilterListener.class);

    @Autowired
    public FilterListener(final RunModeControl runmodeControl, LightsBusiness business, List<RunMode> runModes) {
        this.control = runmodeControl;
        this.business = business;
        this.runModes = runModes;
    }

    @PostConstruct
    public void addListener() {
        business.addListener(this);
    }

    @Override
    public void entryAdded(EntryEvent<String, FilterChangeCommand> event) {
        log.info("Received command " + event);

        for (RunMode mode : runModes) {
            if (mode.identify().getName().equals(event.getValue().getRunMode())) {
                mode.toggleFilter(event.getValue().getFilter());
            }
        }


    }
}
