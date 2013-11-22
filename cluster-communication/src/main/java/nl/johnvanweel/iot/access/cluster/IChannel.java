package nl.johnvanweel.iot.access.cluster;

import nl.johnvanweel.iot.access.cluster.message.GroupMessage;
import org.jgroups.Address;

/**
 *
 */
public interface IChannel {
    void broadcast(GroupMessage message);

    void sendTo(Address to, GroupMessage message);
}
