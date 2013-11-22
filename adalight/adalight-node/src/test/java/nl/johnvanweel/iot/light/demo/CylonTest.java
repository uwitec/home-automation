package nl.johnvanweel.iot.light.demo;

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

        cylon = new Cylon(lightService);
    }

    @Test
    public void testCylon() throws Exception {
        cylon.runRemo();
    }
}
