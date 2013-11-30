package nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster;

import nl.johnvanweel.iot.access.cluster.message.GroupMessage;

/**
 * Message to alter the Runmode of the light system
 */
public class RunmodeGroupMessage extends GroupMessage {
    private String mode;

    public RunmodeGroupMessage(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
