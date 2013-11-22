package nl.johnvanweel.iot.access.cluster.listener;

import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *
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
