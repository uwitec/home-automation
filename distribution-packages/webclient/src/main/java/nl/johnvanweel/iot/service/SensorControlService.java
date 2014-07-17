package nl.johnvanweel.iot.service;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import nl.johnvanweel.iot.sensornetwork.business.SensorDataBusiness;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This service sends a Lights-change message to the Light-capable device.
 */
@Component
public class SensorControlService implements DefaultEntryListener<String, SensorReading> {
	private final Logger log = Logger.getLogger(SensorControlService.class);

	private final SensorDataBusiness sensorDao;

	private SensorReading temperatureEntry;
	private SensorReading lightEntry;

	@Autowired
	public SensorControlService(final SensorDataBusiness sensorDataBusiness) {
		this.sensorDao = sensorDataBusiness;
	}

	@PostConstruct
	public void registerListener() {
		sensorDao.addListener(this, SensorType.LIGHT);
		sensorDao.addListener(this, SensorType.TEMPERATURE);
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
