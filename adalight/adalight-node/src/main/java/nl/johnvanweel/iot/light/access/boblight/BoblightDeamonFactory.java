package nl.johnvanweel.iot.light.access.boblight;

import com.sun.jna.Native;

/**
 * Factory to construct a BoblightDaemon implementation using JNA.
 */
public class BoblightDeamonFactory {
	public BoblightDaemon getInstance() {
		System.setProperty("jna.library.path", "/usr/local/lib/");

		return (BoblightDaemon) Native.loadLibrary("libboblight", BoblightDaemon.class);
	}
}
