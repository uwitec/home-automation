package nl.johnvanweel.iot.sensornetwork.xbee;

import com.rapplogic.xbee.api.PacketListener;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZNetRxIoSampleResponse;
import nl.johnvanweel.iot.sensornetwork.SensorDao;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Listener for Xbee serial connection packets.
 *
 * Stores the received data on the network.
 */
public class DataStoringXbeeSerialSamplePacketListener implements PacketListener {
	private final Logger log = Logger.getLogger(DataStoringXbeeSerialSamplePacketListener.class);

	private final SensorDao dao;

	@Autowired
	public DataStoringXbeeSerialSamplePacketListener(SensorDao dao) {
		this.dao = dao;
	}

	@Override
	public void processResponse(XBeeResponse xBeeResponse) {
		if (!(xBeeResponse instanceof ZNetRxIoSampleResponse)) {
			log.warn("Response is not of the correct type.");
			return;
		}

		ZNetRxIoSampleResponse response = (ZNetRxIoSampleResponse) xBeeResponse;
		dao.storeSensorReading(new SensorReading(System.currentTimeMillis(), response.getAnalog1(), "Light", SensorType.LIGHT));
		dao.storeSensorReading(new SensorReading(System.currentTimeMillis(), response.getAnalog0(), "Temperature", SensorType.TEMPERATURE));
	}
}
