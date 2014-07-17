package nl.johnvanweel.iot.access.cluster.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 1:12 PM
 */
@Component
public class ClusterDataTopicAccess<N extends IStorable> {
	private ITopic<N> dataMap;

	private final String topicName;

	private HazelcastInstance hazelcastInstance;

	public ClusterDataTopicAccess(final String topicName) {
		this.topicName = topicName;
	}

	@PostConstruct
	public void postConstruct() {
		hazelcastInstance = Hazelcast.newHazelcastInstance();

		dataMap = hazelcastInstance.getTopic(topicName);
	}

	@PreDestroy
	public void preDestroy(){
		hazelcastInstance.shutdown();
	}

	public void store(final N storable){
		dataMap.publish(storable);
	}

	public void registerListener(MessageListener<N> listener){
		dataMap.addMessageListener(listener);
	}
}
