package nl.johnvanweel.iot.light.access.cluster;

import nl.johnvanweel.iot.access.cluster.listener.IMessageListener;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunmodeGroupMessage;
import nl.johnvanweel.iot.light.runmode.RunModeControl;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Listens to IlluminationGroupMessage. This will read the new light value from the message and
 * set it.
 */
public class RunmodeListener implements IMessageListener {
    private final RunModeControl control;

    @Autowired
    public RunmodeListener(final RunModeControl runmodeControl) {
        this.control = runmodeControl;
    }


    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof RunmodeGroupMessage;
    }

    @Override
    public void handle(Message message) {
        RunmodeGroupMessage groupMessage = (RunmodeGroupMessage) message.getObject();

        control.start(groupMessage);
    }
}
