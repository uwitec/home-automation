package nl.johnvanweel.iot.light.service;

/**
 * Basic exception for when the LEDs cannot be accessed.
 */
public class LightException extends RuntimeException {
    public LightException(String message) {
        super(message);
    }
}
