package nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster;

/**
 * Defines all known runmodes
 */
public enum RunModes {
    STATIC("static"), SPECTRUM("spectrum"), SPECTRUM_2("spectrum_2"), SUNSET("sunset"), CYLON("cylon");

    private final String name;

    RunModes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
