package nl.johnvanweel.iot.service;

import com.hazelcast.core.EntryAdapter;
import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.sensornetwork.SensorDao;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.predicate.SensorPredicate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This service sends a Lights-change message to the Light-capable device.
 */
@Component
public class SensorControlService extends EntryAdapter<String, SensorReading> {
	private final Logger log = Logger.getLogger(SensorControlService.class);

	private final SensorDao sensorDao;

	private SensorReading temperatureEntry;
	private SensorReading lightEntry;

	@Autowired
	public SensorControlService(final SensorDao sensorDao) {
		this.sensorDao = sensorDao;
	}

	@PostConstruct
	public void registerListener() {
		sensorDao.addListener(this, SensorPredicate.createLight());
		sensorDao.addListener(this, SensorPredicate.createTemperature());
	}

	@Override
	public void entryAdded(EntryEvent<String, SensorReading> event) {
		switch (event.getValue().getSensorType()) {
			case LIGHT:
				lightEntry = event.getValue();
				break;
			case TEMPERATURE:
				temperatureEntry = event.getValue();
				break;
			default:
				log.warn("Received unknown sensor type: " + event.getValue().getSensorType());
		}
	}

	public SensorReading[] getReadings() {
		return new SensorReading[]{temperatureEntry, lightEntry};
	}
}
