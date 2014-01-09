package nl.johnvanweel.iot.light.sunset;

/**
 * Generic service for getting the time of the sunset.
 */
public interface SunsetService {
    SunState getSunState();

    double getTransitionPercentage();
}
