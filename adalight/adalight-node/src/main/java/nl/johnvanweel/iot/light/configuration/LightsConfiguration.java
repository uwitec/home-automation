package nl.johnvanweel.iot.light.configuration;

/**
 * Configuration bean for the lights.
 */
public class LightsConfiguration {
    private int numberOfLights = 20;

    public int getNumberOfLights() {
        return numberOfLights;
    }

    public void setNumberOfLights(int numberOfLights) {
        this.numberOfLights = numberOfLights;
    }
}
