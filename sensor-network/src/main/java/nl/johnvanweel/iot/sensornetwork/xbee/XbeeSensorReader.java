package nl.johnvanweel.iot.sensornetwork.xbee;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxIoSampleResponse;
import nl.johnvanweel.iot.sensornetwork.SensorDao;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public class XbeeSensorReader {
	private final SensorDao dao;

	@Autowired
	public XbeeSensorReader(SensorDao dao) {
		this.dao = dao;
	}

	@PostConstruct
	public void startSensorReceiverThread() {

		XBee xBee = new XBee();

		try {
			xBee.open("/dev/ttyUSB0", 9600);
			xBee.addPacketListener(response -> {
				ZNetRxIoSampleResponse response1 = (ZNetRxIoSampleResponse) response;
				System.out.println("==========================================================");
				dao.storeSensorReading(new SensorReading(System.currentTimeMillis(), response1.getAnalog1(), "Light", SensorType.LIGHT));
				dao.storeSensorReading(new SensorReading(System.currentTimeMillis(), response1.getAnalog0(), "Temperature", SensorType.TEMPERATURE));
				System.out.println(response1.getAnalog0());
				System.out.println(response1.getAnalog1());
				System.out.println("==========================================================");

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
