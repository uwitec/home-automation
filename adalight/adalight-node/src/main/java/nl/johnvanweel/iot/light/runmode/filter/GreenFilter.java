package nl.johnvanweel.iot.light.runmode.filter;

/**
 * Created by john on 3/20/16.
 */
public class GreenFilter extends AbstractLightFilter {
    @Override
    public String getName() {
        return "green";
    }

    @Override
    public int[] filter(int red, int green, int blue) {
        return new int[]{red, 0, blue};
    }
}
