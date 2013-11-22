package nl.johnvanweel.iot.music.access;

import nl.johnvanweel.iot.music.model.messaging.GroupMessage;
import org.jgroups.Address;

/**
 *
 */
public interface IChannel {
    void broadcast(GroupMessage message);

    void sendTo(Address to, GroupMessage message);
}
