package nl.johnvanweel.iot.access.cluster.business;

import com.hazelcast.core.EntryEvent;
import nl.johnvanweel.iot.access.cluster.capabilities.Capability;
import nl.johnvanweel.iot.access.cluster.hazelcast.ClusterDataMapAccess;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.access.cluster.message.NodeIdentification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Business component for cluster communication
 */
public class ClusterBusiness implements DefaultEntryListener<String, NodeIdentification> {
	private final Logger log = Logger.getLogger(ClusterBusiness.class);
	private final ClusterDataMapAccess<NodeIdentification> clusterDataMapAccess;

	private final Capability[] capabilities;
	private final String hostName;

	private final Map<String, NodeIdentification> allDevices = new HashMap<>();

	@Autowired
	public ClusterBusiness(@Qualifier("discoveryDataAccess") ClusterDataMapAccess<NodeIdentification> clusterDataMapAccess, Capability...capabilities) throws UnknownHostException {
		this.clusterDataMapAccess = clusterDataMapAccess;
		this.capabilities = capabilities;

		hostName = InetAddress.getLocalHost().getHostName();
	}

	@PostConstruct
	public void registerSelf() {
		log.info("Registering on cluster " + hostName);
		clusterDataMapAccess.store(new NodeIdentification(hostName, capabilities));
	}

	@PreDestroy
	public void unregisterSelf() {
		log.info("Un-Registering on cluster " + hostName);
		clusterDataMapAccess.remove(new NodeIdentification(hostName, capabilities));
	}

	@PostConstruct
	public void registerListener() {
		log.info("Listening on cluster.");
		clusterDataMapAccess.registerListener(this);

		for (NodeIdentification identification : clusterDataMapAccess.getAll()){
			log.info("Pre-loading device " + identification.getHost());
			allDevices.put(identification.getHost(), identification);
		}
	}

	@Override
	public void entryAdded(EntryEvent<String, NodeIdentification> event) {
		log.info("Added node " + event.getKey());
		allDevices.put(event.getKey(), event.getValue());
	}

	@Override
	public void entryRemoved(EntryEvent<String, NodeIdentification> event) {
		log.info("Removed node " + event.getKey());
		allDevices.remove(event.getKey());
	}

	public Map<String, NodeIdentification> getAllDevices() {
		return new HashMap<>(allDevices);
	}
}
