package nl.johnvanweel.iot.light.step;

/**
 * Marks that a class is able to step once in changing the lights.
 * Each step should only send the pixel configuration once to the LEDs.
 */
public interface Steppable {
    void step() throws InterruptedException;
}
