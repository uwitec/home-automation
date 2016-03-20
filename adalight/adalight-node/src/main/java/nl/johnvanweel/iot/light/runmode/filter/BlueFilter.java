package nl.johnvanweel.iot.light.runmode.filter;

/**
 * Created by john on 3/20/16.
 */
public class BlueFilter extends AbstractLightFilter {
    @Override
    public String getName() {
        return "blue";
    }

    @Override
    public int[] filter(int red, int green, int blue) {
        return new int[]{0, 0, blue};
    }
}
