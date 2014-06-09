package nl.johnvanweel.iot.access.cluster.listener;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 8:00 PM
 */
public interface DefaultEntryListener<K,V> extends EntryListener<K,V> {
	@Override
	default void entryAdded(EntryEvent<K,V> event) {

	}

	@Override
	default void entryRemoved(EntryEvent<K,V> event) {

	}

	@Override
	default void entryUpdated(EntryEvent<K,V> event) {

	}

	@Override
	default void entryEvicted(EntryEvent<K,V> event) {

	}
}
