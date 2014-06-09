package nl.johnvanweel.iot.sensornetwork;

import com.hazelcast.core.EntryListener;
import com.hazelcast.query.Predicate;
import nl.johnvanweel.iot.access.cluster.hazelcast.ClusterDataAccess;
import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 1:40 PM
 */
@Component
public class SensorDao {
	private final ClusterDataAccess clusterDataAccess;

	@Autowired
	public SensorDao(final ClusterDataAccess clusterDataAccess) {
		this.clusterDataAccess = clusterDataAccess;
	}

	public void storeSensorReading(final SensorReading reading){
		clusterDataAccess.store(reading);
	}

	public Set<Map.Entry<String,IStorable>> findSensorReadings(Predicate predicate) {
		return clusterDataAccess.find(predicate);
	}

	public void addListener(EntryListener listener, Predicate predicate) {
		   clusterDataAccess.registerListener(listener, predicate);
	}
}
