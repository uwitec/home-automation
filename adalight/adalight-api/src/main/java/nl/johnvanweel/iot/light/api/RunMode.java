package nl.johnvanweel.iot.light.api;

/**
 * Defines all known runmodes
 */
public enum RunMode {
	STATIC("static"), SPECTRUM("spectrum"), SPECTRUM_2("spectrum_2"), SUNSET("sunset"), CYLON("cylon"), SENSOR_LIG("sensor_lig"), SENSOR_TMP("sensor_tmp"), OFF("off");

	private final String name;

	RunMode(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
