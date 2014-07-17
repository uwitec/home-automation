package nl.johnvanweel.iot.access.cluster.listener;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

/**
 * Listens for messages on a Topic
 */
public interface DefaultMessageListener<V> extends MessageListener<V> {
	@Override
	default void onMessage(Message<V> vMessage){}
}
