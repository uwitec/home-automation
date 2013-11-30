package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.configuration.LightsConfiguration;
import nl.johnvanweel.iot.light.service.dummy.DummyILightService;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class SpectrumTest {
    Spectrum spectrum;

    @Before
    public void setUp() throws Exception {
        spectrum = new Spectrum(new LightsConfiguration(), new DummyILightService());

    }

    @Test
    public void tests() throws Exception {
        for (int i = 0; i < 100; i++) {

            spectrum.step();
        }
    }
}
