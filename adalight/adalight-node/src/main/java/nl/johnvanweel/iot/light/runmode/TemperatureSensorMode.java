package nl.johnvanweel.iot.light.runmode;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.light.runmode.step.SettableGradient;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import nl.johnvanweel.iot.sensornetwork.business.SensorDataBusiness;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Displays all colors
 */
public class TemperatureSensorMode extends nl.johnvanweel.iot.light.runmode.RunMode implements DefaultEntryListener<String, SensorReading> {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.SENSOR_TMP.getName();
    private final Logger log = Logger.getLogger(TemperatureSensorMode.class);
    private final SettableGradient sensor;
    private final SensorDataBusiness sensorDataBusiness;

    @Autowired
    public TemperatureSensorMode(final SettableGradient sensor, final SensorDataBusiness sensorDataBusiness) {
        this.sensor = sensor;
        this.sensorDataBusiness = sensorDataBusiness;
    }

    @PostConstruct
    public void registerListener() {
        sensorDataBusiness.addListener(this, SensorType.TEMPERATURE);
    }

    @Override
    protected void executeStep() {
        sensor.step();
    }

    public LightRunMode identify() {
        return new LightRunMode(RUNMODE);
    }

    @Override
    public void toggleFilter(String name) {
        // No filters
    }

    @Override
    public void entryAdded(EntryEvent<String, SensorReading> event) {
        log.info("Added entry.");
        // Until real sensors, assume the value is between 0 and 100.
        assert (event.getValue().getValue() < 1023 && event.getValue().getValue() >= 0);
        int value = (int) (event.getValue().getValue() * 20 / 1023);
        log.info("Setting new value " + value);
        sensor.setGradientStep(value);
    }
}
