package nl.johnvanweel.iot.sensornetwork;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.zigbee.ZNetRxIoSampleResponse;
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
	public void startSensorReceiverThread() throws Throwable {
		XBee xBee = new XBee();
		xBee.open("/dev/ttyUSB0", 9600);
		xBee.addPacketListener(response -> {
			ZNetRxIoSampleResponse response1 = (ZNetRxIoSampleResponse) response;
			System.out.println("==========================================================");
			dao.storeSensorReading(new SensorReading(System.currentTimeMillis(), response1.getAnalog0(), "Light", SensorType.LIGHT));
			dao.storeSensorReading(new SensorReading(System.currentTimeMillis(), response1.getAnalog0(), "Temperature", SensorType.TEMPERATURE));
			System.out.println(response1.getAnalog0());
			System.out.println(response1.getAnalog1());
			System.out.println("==========================================================");

		});
	}
}
