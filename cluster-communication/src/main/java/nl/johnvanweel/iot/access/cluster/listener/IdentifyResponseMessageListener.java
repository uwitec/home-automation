package nl.johnvanweel.iot.access.cluster.listener;

import nl.johnvanweel.iot.access.cluster.message.IdentifyResponseMessage;
import org.jgroups.Message;

/**
 *
 */
public abstract class IdentifyResponseMessageListener implements IMessageListener {
    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof IdentifyResponseMessage;
    }

    public abstract void acceptMessage(IdentifyResponseMessage message);

    @Override
    public void handle(Message message) {
        acceptMessage((IdentifyResponseMessage) message.getObject());
    }
}
