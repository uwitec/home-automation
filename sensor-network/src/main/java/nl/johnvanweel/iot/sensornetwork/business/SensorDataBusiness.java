package nl.johnvanweel.iot.sensornetwork.business;

import nl.johnvanweel.iot.access.cluster.hazelcast.ClusterDataMapAccess;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;
import nl.johnvanweel.iot.sensornetwork.predicate.SensorPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Business component for sensor data
 */
public class SensorDataBusiness {
	private final ClusterDataMapAccess<SensorReading> sensorDao;

	@Autowired
	public SensorDataBusiness(@Qualifier("sensorDataAccess") ClusterDataMapAccess<SensorReading> sensorDao) {
		this.sensorDao = sensorDao;
	}

	public void addListener(DefaultEntryListener<?, ?> listener, SensorType sensorType) {
	   sensorDao.registerListener(listener, SensorPredicate.create(sensorType));
	}

	public void store(SensorReading sensorReading){
		sensorDao.store(sensorReading);
	}
}
