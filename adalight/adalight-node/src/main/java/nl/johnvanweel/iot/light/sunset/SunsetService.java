package nl.johnvanweel.iot.light.sunset;

/**
 * Generic service for getting the time of the sunset.
 */
public interface SunsetService {
    /**
     * Gets the time for the next sunset.
     */
    long getSunsetTime();
}
