package nl.johnvanweel.iot.light.access.cluster;

import nl.johnvanweel.iot.access.cluster.message.GroupMessage;

/**
 *
 */
public class IlluminationGroupMessage extends GroupMessage {
    private int[] rgb;

    public IlluminationGroupMessage(int[] rgb) {
        this.rgb = rgb;
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }
}
