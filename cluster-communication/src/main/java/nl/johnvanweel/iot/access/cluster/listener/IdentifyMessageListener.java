package nl.johnvanweel.iot.access.cluster.listener;

import nl.johnvanweel.iot.access.cluster.JGroupsChannel;
import nl.johnvanweel.iot.access.cluster.exception.ListenerException;
import nl.johnvanweel.iot.access.cluster.message.IdentifyGroupMessage;
import nl.johnvanweel.iot.access.cluster.message.IdentifyResponseMessage;
import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 */
public class IdentifyMessageListener implements IMessageListener {
    private Logger logger = Logger.getLogger(IMessageListener.class);

    private final JGroupsChannel channel;

    @Autowired(required = false)
    private IMessageListener[] allMessageListeners;

    @Autowired
    public IdentifyMessageListener(final JGroupsChannel channel) {
        this.channel = channel;
    }


    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof IdentifyGroupMessage;
    }

    @Override
    public void handle(Message message) {
        String[] capabilities = getCapabilities();
        String hostName = getHostName();

        IdentifyResponseMessage responseMessage = new IdentifyResponseMessage(hostName, capabilities);

        channel.sendTo(message.getSrc(), responseMessage);
    }

    private String getHostName() {
        String hostName;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.warn("Cannot find host: ", e);
            throw new ListenerException("Cannot get hostname.", e);
        }
        return hostName;
    }

    private String[] getCapabilities() {
        String[] capabilities = new String[allMessageListeners.length];

        for (int i = 0; i < allMessageListeners.length; i++) {
            capabilities[i] = allMessageListeners[i].getClass().getName();
        }
        return capabilities;
    }

    public IMessageListener[] getAllMessageListeners() {
        return allMessageListeners;
    }

    public void setAllMessageListeners(IMessageListener... allMessageListeners) {
        this.allMessageListeners = allMessageListeners;
    }
}
