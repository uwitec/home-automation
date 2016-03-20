package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.runmode.step.Cylon;
import nl.johnvanweel.iot.light.service.dummy.DummyILightService;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class CylonTest {
    private Cylon cylon;
    private DummyILightService lightService;

    @Before
    public void setUp() throws Exception {
        lightService = new DummyILightService();

        cylon = new Cylon(lightService, 25);
    }

    @Test
    public void testCylon() throws Exception {
        cylon.step();
    }
}
