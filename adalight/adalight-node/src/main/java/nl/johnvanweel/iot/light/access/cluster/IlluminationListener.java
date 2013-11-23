package nl.johnvanweel.iot.light.access.cluster;

import nl.johnvanweel.iot.access.cluster.listener.IMessageListener;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.service.ILightService;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Listens to IlluminationGroupMessage. This will read the new light value from the message and
 * set it.
 */
public class IlluminationListener implements IMessageListener {
    private final ILightService lightService;

    @Autowired
    public IlluminationListener(final ILightService lightService) {
        this.lightService = lightService;
    }


    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof IlluminationGroupMessage;
    }

    @Override
    public void handle(Message message) {
        IlluminationGroupMessage groupMessage = (IlluminationGroupMessage) message.getObject();

        lightService.allPixels(groupMessage.getRgb()[0], groupMessage.getRgb()[1], groupMessage.getRgb()[2]);
        lightService.send();
    }
}
