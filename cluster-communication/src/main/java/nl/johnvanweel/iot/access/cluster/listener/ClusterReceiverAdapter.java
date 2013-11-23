package nl.johnvanweel.iot.access.cluster.listener;

import nl.johnvanweel.iot.access.cluster.message.GroupMessage;
import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Generic adapter that receives messages from the cluster and attempts
 * to find the proper handler for it.
 */
public class ClusterReceiverAdapter extends ReceiverAdapter {
    private Logger logger = Logger.getLogger(ClusterReceiverAdapter.class);

    private List<IMessageListener> messageListeners;
    private DefaultMessageListener defaultMessageListener = new DefaultMessageListener();

    @Autowired
    public ClusterReceiverAdapter(IMessageListener... messageListeners) {
        this.messageListeners = Arrays.asList(messageListeners);
    }

    @Override
    public void receive(Message msg) {
        logger.info("Received message with payload " + msg.getObject());

        if (msg.getObject() instanceof GroupMessage) {
            GroupMessage message = (GroupMessage) msg.getObject();
            message.setSender(msg.getSrc());
            msg.setObject(message);
        }

        messageListeners.stream().filter(canHandle(msg)).findFirst().orElse(defaultMessageListener).handle(msg);
        logger.debug("Message handled with payload " + msg.getObject());
    }

    private Predicate<? super IMessageListener> canHandle(Message msg) {
        return e -> e.canHandle(msg);
    }

    private class DefaultMessageListener implements IMessageListener {
        @Override
        public void handle(Message message) {
            logger.warn("Cannot handle message " + message.getObject() + ".");
        }
    }
}
