package nl.johnvanweel.iot.light.service;

/**
 * Interface to hide the lowlevel code required for communicating with the LEDs
 */
public interface ILightService {
    /**
     * Sets a pixel to a new color. This does not send the new state to the LED strand.
     *
     * @param pixelNumber the number of the pixel, must be higher than 0.
     * @param red         amount red
     * @param green       amount green
     * @param blue        amount blue
     */
    void setPixel(int pixelNumber, int red, int green, int blue);

    /**
     * Changes all pixels to the given color. This does not send the new state to the LED strand.
     *
     * @param red   amount red
     * @param green amount green
     * @param blue  amount blue
     */
    void allPixels(int red, int green, int blue);

    /**
     * Sends all set-pixel values and resets the state
     */
    void send();
}
