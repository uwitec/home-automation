package nl.johnvanweel.iot.access.cluster.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * User: jvweel
 * Date: 7/17/14
 * Time: 8:27 PM
 */
public class HazelCastServer {
	private final HazelcastInstance server;

	public HazelCastServer(){
		this.server = Hazelcast.newHazelcastInstance();
	}

	@PreDestroy
	public void shutdown(){
		server.shutdown();
	}
}
