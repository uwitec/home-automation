package nl.johnvanweel.iot.service;

import nl.johnvanweel.iot.access.cluster.IChannel;
import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.IlluminationGroupMessage;
import nl.johnvanweel.iot.web.model.IotNode;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LightsControlServiceTest {
    private LightsControlService service;

    private IChannel channel;

    @Before
    public void setUp() throws Exception {
        channel = mock(IChannel.class);
        service = new LightsControlService(channel);
    }

    @Test
    public void testLightsBroadcast() throws Exception {
        service.changeLight(mock(IotNode.class), "FF00FF");

        verify(channel).broadcast(eq(new IlluminationGroupMessage(new int[]{255, 0, 255})));
    }
}
