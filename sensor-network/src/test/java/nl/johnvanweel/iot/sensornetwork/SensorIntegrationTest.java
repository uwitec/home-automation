package nl.johnvanweel.iot.sensornetwork;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;
import nl.johnvanweel.iot.sensornetwork.predicate.TemperatureSensorPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 1:54 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/sensor-context.xml"})
public class SensorIntegrationTest {
	@Autowired
	public SensorDao dao;

	@Test
	public void testSensorStorage() throws InterruptedException {
		SensorReading expectedReading = null;
		for (int i = 0; i < 100; i++) {
			expectedReading = new SensorReading(System.currentTimeMillis(), i, "1", SensorType.TEMPERATURE);
			dao.storeSensorReading(expectedReading);
			Thread.sleep(1000);

		}

		Set<Map.Entry<String, IStorable>> results = dao.findSensorReadings(TemperatureSensorPredicate.create());

		final SensorReading lastReading = expectedReading;
		assertTrue(results.parallelStream().anyMatch(input -> input.getValue().equals(lastReading)));

	}
}
