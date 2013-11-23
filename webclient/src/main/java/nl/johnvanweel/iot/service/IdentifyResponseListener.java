package nl.johnvanweel.iot.service;

import nl.johnvanweel.iot.access.cluster.listener.IdentifyResponseMessageListener;
import nl.johnvanweel.iot.access.cluster.message.IdentifyResponseMessage;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Accepts identification responses.
 * Each response will be placed on the list of known devices.
 * TODO: Add listeners for removed devices.
 * TODO: Remove the heartbeat.
 * TODO: Add listener for added devices.
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
        availableDevicesService.add(new IotNode(message.getHost(), message.getSender(), message.getCapabilities()));
    }
}
