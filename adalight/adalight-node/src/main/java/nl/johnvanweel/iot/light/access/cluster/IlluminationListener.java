package nl.johnvanweel.iot.light.access.cluster;

import nl.johnvanweel.iot.access.cluster.listener.IMessageListener;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.light.runmode.RunModeControl;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Listens to IlluminationGroupMessage. This will read the new light value from the message and
 * set it.
 */
public class IlluminationListener implements IMessageListener {
    private final RunModeControl control;

    @Autowired
    public IlluminationListener(final RunModeControl runmodeControl) {
        this.control = runmodeControl;
    }


    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof IlluminationGroupMessage;
    }

    @Override
    public void handle(Message message) {
        IlluminationGroupMessage groupMessage = (IlluminationGroupMessage) message.getObject();

        control.reconfigure(groupMessage);
    }
}
