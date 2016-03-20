package nl.johnvanweel.iot.light.runmode.filter;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import nl.johnvanweel.iot.sensornetwork.business.SensorDataBusiness;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Displays all colors
 */
public class LightSensorFilter extends AbstractLightFilter implements DefaultEntryListener<String, SensorReading> {
    public static final String RUNMODE = nl.johnvanweel.iot.light.api.RunMode.SENSOR_LIG.getName();
    private final Logger log = Logger.getLogger(LightSensorFilter.class);

    private final SensorDataBusiness sensorDataBusiness;
    private int percentage = 100;

    @Autowired
    public LightSensorFilter(final SensorDataBusiness sensorDataBusiness) {
        this.sensorDataBusiness = sensorDataBusiness;
    }

    @PostConstruct
    public void registerListener() {
        sensorDataBusiness.addListener(this, SensorType.LIGHT);
    }

    public LightRunMode identify() {
        return new LightRunMode(RUNMODE);
    }

    @Override
    public void entryAdded(EntryEvent<String, SensorReading> event) {
        assert (event.getValue().getValue() < 1023 && event.getValue().getValue() >= 0);
        int value = 100 - (int) (event.getValue().getValue() * 100 / 1023);
        log.info("Setting new value " + value);
        percentage = value;
    }

    @Override
    public String getName() {
        return "light sensor";
    }

    @Override
    public int[] filter(int red, int green, int blue) {
        final int newRed = (red * percentage) / 100;
        final int newGreen = (green * percentage) / 100;
        final int newBlue = (blue * percentage) / 100;

        return new int[]{newRed, newGreen, newBlue};
    }
}
