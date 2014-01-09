package nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster;

/**
 *
 */
public class MyIllumiGM extends IlluminationGroupMessage {
    private int[][] rgbs;

    public MyIllumiGM(int[] rgb) {
        super(rgb);
    }

    public void setRgbs(int[][] rgbs) {

        this.rgbs = rgbs;
    }

    public int[][] getRgbs() {
        return rgbs;
    }
}
