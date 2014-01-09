package nl.johnvanweel.iot.light.sunset;

/**
 *
 */
public class DummySunsetService implements SunsetService {
    @Override
    public SunState getSunState() {
        return SunState.RISING;
    }

    @Override
    public double getTransitionPercentage() {
        return 90;
    }
}
