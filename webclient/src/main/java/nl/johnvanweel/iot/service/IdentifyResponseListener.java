package nl.johnvanweel.iot.service;

import nl.johnvanweel.iot.access.cluster.listener.IdentifyResponseMessageListener;
import nl.johnvanweel.iot.access.cluster.message.IdentifyResponseMessage;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class IdentifyResponseListener extends IdentifyResponseMessageListener {
    private final AvailableDevicesService availableDevicesService;

    @Autowired
    public IdentifyResponseListener(final AvailableDevicesService availableDevicesService) {
        this.availableDevicesService = availableDevicesService;
    }

    @Override
    public void acceptMessage(IdentifyResponseMessage message) {
        availableDevicesService.add(new IotNode(message.getHost(), message.getCapabilities()));
    }
}
