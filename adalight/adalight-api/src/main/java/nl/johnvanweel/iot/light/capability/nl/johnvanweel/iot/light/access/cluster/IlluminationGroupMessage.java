package nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster;

import nl.johnvanweel.iot.access.cluster.message.GroupMessage;

import java.util.Arrays;

/**
 * Group message to change the lighting color.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IlluminationGroupMessage that = (IlluminationGroupMessage) o;

        if (!Arrays.equals(rgb, that.rgb)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return rgb != null ? Arrays.hashCode(rgb) : 0;
    }
}
