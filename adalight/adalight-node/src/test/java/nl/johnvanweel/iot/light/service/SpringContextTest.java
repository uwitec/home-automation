package nl.johnvanweel.iot.light.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import nl.johnvanweel.iot.access.cluster.IChannel;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Small test to verify most of the spring context. Used for debugging without deploying to a light-capable device.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/access-test.xml", "/META-INF/spring/cluster-access.xml"})
@Ignore
public class SpringContextTest {
    @Autowired
    private IChannel channel;

    @Test
    public void testLights() throws Exception {
        for (int i = 0; i < 255; i++) {
            channel.broadcast(new IlluminationGroupMessage(new int[]{i, 0, 0}));
            Thread.sleep(100);
        }
    }
}