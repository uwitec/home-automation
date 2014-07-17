package nl.johnvanweel.iot.access.cluster.hazelcast;

import com.hazelcast.core.EntryListener;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import nl.johnvanweel.iot.access.cluster.message.NodeIdentification;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Access component for Hazelcast Maps
 */
@Component
public class ClusterDataMapAccess<N extends IStorable> {
	private final Logger log = Logger.getLogger(ClusterDataMapAccess.class);

	private IMap<String, N> dataMap;

	private final String mapName;

	private HazelcastInstance hazelcastInstance;

	public ClusterDataMapAccess(final String mapName) {
		this.mapName = mapName;
	}

	@PostConstruct
	public void postConstruct() {
		hazelcastInstance = Hazelcast.newHazelcastInstance();

		dataMap = hazelcastInstance.getMap(mapName);
	}

	@PreDestroy
	public void preDestroy() {
		hazelcastInstance.shutdown();
	}

	public void store(final N storable) {
		log.info("Storing item " + storable);
		dataMap.put(storable.getKey(), storable);
	}

	public Set<Map.Entry<String, N>> find(final Predicate predicate) {
		return dataMap.entrySet(predicate);
	}

	public void registerListener(EntryListener listener, Predicate predicate) {
		dataMap.addEntryListener(listener, predicate, true);
	}

	public void registerListener(EntryListener listener) {
		dataMap.addEntryListener(listener, true);
	}

	public Collection<N> getAll() {
		return dataMap.values();
	}

	public void remove(N item) {
		dataMap.remove(item);
	}
}
