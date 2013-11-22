package nl.johnvanweel.iot.access.cluster;

import org.jgroups.Message;

/**
 *
 */
public interface IMessageListener {
    default boolean canHandle(Message message) {
        return true;
    }

    void handle(Message message);
}
