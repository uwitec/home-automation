package nl.johnvanweel.iot.sensornetwork.xbee;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxIoSampleResponse;
import gnu.io.CommPortIdentifier;
import nl.johnvanweel.iot.sensornetwork.SensorDao;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.annotation.Native;

/**
 *
 */
@Component
public class XbeeSensorReader {
	private final Logger log = Logger.getLogger(XbeeSensorReader.class);

	private final SensorDao dao;

	private XBee xBee = new XBee();

	@Autowired
	public XbeeSensorReader(SensorDao dao) {
		this.dao = dao;
	}

	@PostConstruct
	public void startSensorReceiverThread() {
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

	@PreDestroy
	public void preDestroy() {
		xBee.close();
	}
}
