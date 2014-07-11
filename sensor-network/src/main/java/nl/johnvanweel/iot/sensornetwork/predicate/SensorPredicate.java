package nl.johnvanweel.iot.sensornetwork.predicate;

import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.sensornetwork.SensorType;


/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 2:00 PM
 */
public class SensorPredicate {
	public static Predicate createLight(){
		EntryObject e = new PredicateBuilder().getEntryObject();
		Predicate predicate = e.get("sensorType").equal(SensorType.LIGHT);

		return predicate;
	}

	public static Predicate createTemperature(){
		EntryObject e = new PredicateBuilder().getEntryObject();
		Predicate predicate = e.get("sensorType").equal(SensorType.TEMPERATURE);

		return predicate;
	}
}
