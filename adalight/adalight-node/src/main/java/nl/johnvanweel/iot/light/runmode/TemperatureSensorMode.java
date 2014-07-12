package nl.johnvanweel.iot.light.runmode;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunModes;
import nl.johnvanweel.iot.light.step.SettableGradient;
import nl.johnvanweel.iot.sensornetwork.SensorDao;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.predicate.SensorPredicate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Displays all colors
 */
public class TemperatureSensorMode extends RunMode implements DefaultEntryListener<String, SensorReading> {
	private final Logger log = Logger.getLogger(TemperatureSensorMode.class);

    public static final String RUNMODE = RunModes.SENSOR.getName();

    private final SettableGradient sensor;
	private final SensorDao sensorDao;

    @Autowired
    public TemperatureSensorMode(final SettableGradient sensor, final SensorDao sensorDao) {
        this.sensor = sensor;
		this.sensorDao = sensorDao;
	}

	@PostConstruct
	public void registerListener(){
		sensorDao.addListener(this, SensorPredicate.createTemperature());
	}

    @Override
    protected void executeStep() {
        sensor.step();
    }

    @Override
    public String identify() {
        return RUNMODE;
    }

    @Override
    protected void reconfigure(IlluminationGroupMessage message) {

    }

	@Override
	public void entryAdded(EntryEvent<String, SensorReading> event) {
		log.info("Added entry.");
		// Until real sensors, assume the value is between 0 and 100.
		assert(event.getValue().getValue() <100 && event.getValue().getValue() >= 0);
		int value = (int) (event.getValue().getValue()*20/100);
		log.info("Setting new value " + value);
		sensor.setGradientStep(value);
	}
}
