package nl.johnvanweel.iot.service;

import nl.johnvanweel.iot.access.cluster.IChannel;
import nl.johnvanweel.iot.access.cluster.message.IdentifyGroupMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class IotService {
    private final IChannel channel;

    @Autowired
    public IotService(final IChannel channel) {
        this.channel = channel;
    }

    public void listDevices() {
        channel.broadcast(new IdentifyGroupMessage());
    }
}
