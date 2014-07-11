package nl.johnvanweel.iot.service;

import com.rapplogic.xbee.api.XBeeResponse;
import nl.johnvanweel.iot.access.cluster.IChannel;
import nl.johnvanweel.iot.access.cluster.message.IdentifyGroupMessage;
import nl.johnvanweel.iot.web.model.IotNode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class AvailableDevicesService {
    private Logger logger = Logger.getLogger(AvailableDevicesService.class);

    private final IChannel channel;

    private Map<String, IotNode> knownDevices = new HashMap<>();

    @Autowired
    public AvailableDevicesService(final IChannel channel) {
        this.channel = channel;
    }

    public void reload() {
        channel.broadcast(new IdentifyGroupMessage());
    }

    public void add(IotNode iotNode) {
        logger.info("Adding device " + iotNode.getName());
		XBeeResponse r;
        knownDevices.put(iotNode.getName(), iotNode);
    }

    public IotNode[] getAllDevices() {
        return knownDevices.values().toArray(new IotNode[knownDevices.size()]);
    }

    public IotNode getDevice(String name) {
        return knownDevices.get(name);
    }
}
