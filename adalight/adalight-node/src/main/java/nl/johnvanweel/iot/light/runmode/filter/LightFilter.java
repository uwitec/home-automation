package nl.johnvanweel.iot.light.runmode.filter;

/**
 * Created by john on 3/20/16.
 */
public interface LightFilter {
    String getName();
    boolean isActive();
    int[] filter(int red, int green, int blue);

    void toggle();
}
