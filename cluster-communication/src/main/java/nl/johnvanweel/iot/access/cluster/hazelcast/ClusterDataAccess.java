package nl.johnvanweel.iot.access.cluster.hazelcast;

import com.hazelcast.core.EntryListener;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Set;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 1:12 PM
 */
@Component
public class ClusterDataAccess {
	private IMap<String, IStorable> dataMap;

	private final String mapName;

	private HazelcastInstance hazelcastInstance;

	public ClusterDataAccess(final String mapName) {
		this.mapName = mapName;
	}

	@PostConstruct
	public void postConstruct() {
		hazelcastInstance = Hazelcast.newHazelcastInstance();

		dataMap = hazelcastInstance.getMap(mapName);
	}

	@PreDestroy
	public void preDestroy(){
		hazelcastInstance.shutdown();
	}

	public void store(final IStorable storable){
		dataMap.put(storable.getKey(), storable);
	}

	public Set<Map.Entry<String, IStorable>> find(final Predicate predicate) {
		return dataMap.entrySet(predicate);
	}

	public void registerListener(EntryListener listener, Predicate predicate){
		dataMap.addEntryListener(listener, predicate, true);
	}
}
