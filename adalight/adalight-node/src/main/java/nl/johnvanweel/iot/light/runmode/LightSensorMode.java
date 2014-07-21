package nl.johnvanweel.iot.light.runmode;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.step.Static;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import nl.johnvanweel.iot.sensornetwork.business.SensorDataBusiness;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Displays all colors
 */
public class LightSensorMode extends RunMode implements DefaultEntryListener<String, SensorReading> {
	public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.SENSOR_LIG.getName();
	private final Logger log = Logger.getLogger(LightSensorMode.class);
	private final Static step;
	private final SensorDataBusiness sensorDataBusiness;

	@Autowired
	public LightSensorMode(final Static step, final SensorDataBusiness sensorDataBusiness) {
		this.step = step;
		this.sensorDataBusiness = sensorDataBusiness;
	}

	@PostConstruct
	public void registerListener() {
		sensorDataBusiness.addListener(this, SensorType.LIGHT);
	}

	@Override
	protected void executeStep() {
		step.step();
	}

	@Override
	public String identify() {
		return RUNMODE;
	}

	@Override
	protected void reconfigure(int[] message) {
		step.setColor(message);
	}

	@Override
	public void entryAdded(EntryEvent<String, SensorReading> event) {
		assert (event.getValue().getValue() < 1023 && event.getValue().getValue() >= 0);
		int value = 100 - (int) (event.getValue().getValue() * 100 / 1023);
		log.info("Setting new value " + value);
		step.setPercentage(value);
	}
}
