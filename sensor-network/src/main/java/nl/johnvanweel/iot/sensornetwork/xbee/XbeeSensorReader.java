package nl.johnvanweel.iot.sensornetwork.xbee;

import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 */
@Component
public class XbeeSensorReader {
	private final Logger log = Logger.getLogger(XbeeSensorReader.class);


	private final XBee xBee;
	private final DataStoringXbeeSerialSamplePacketListener listener;

	private String serialPort = "/dev/ttyUSB0";
	private int serialBaudRate = 9600;

	@Autowired
	public XbeeSensorReader(XBee xBee, DataStoringXbeeSerialSamplePacketListener listener) {
		this.xBee = xBee;
		this.listener = listener;
	}

	@PostConstruct
	public void startSensorReceiverThread() {
		try {
			xBee.open(serialPort, serialBaudRate);
		} catch (XBeeException e) {
			log.warn("Cannot open XBee.", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		xBee.addPacketListener(listener);
	}

	@PreDestroy
	public void preDestroy() {
		xBee.close();
	}

	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}

	public void setSerialBaudRate(int serialBaudRate) {
		this.serialBaudRate = serialBaudRate;
	}
}
