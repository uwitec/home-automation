package nl.johnvanweel.iot.access.cluster.listener;

import nl.johnvanweel.iot.access.cluster.JGroupsChannel;
import nl.johnvanweel.iot.access.cluster.capabilities.Capability;
import nl.johnvanweel.iot.access.cluster.exception.ListenerException;
import nl.johnvanweel.iot.access.cluster.message.IdentifyGroupMessage;
import nl.johnvanweel.iot.access.cluster.message.IdentifyResponseMessage;
import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Listens to messages requesting device identification.
 */
public class IdentifyMessageListener implements IMessageListener {
    private Logger logger = Logger.getLogger(IMessageListener.class);

    private final JGroupsChannel channel;
    private Capability[] capabilities;

    @Autowired
    public IdentifyMessageListener(final JGroupsChannel channel, final Capability... capabilities) {
        this.channel = channel;
        this.capabilities = capabilities;
    }


    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof IdentifyGroupMessage;
    }

    @Override
    public void handle(Message message) {
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
}
