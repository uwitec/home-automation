package nl.johnvanweel.iot.music.access;

import org.hamcrest.core.IsInstanceOf;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@Ignore
public class JGroupsChannelTest {
    private JGroupsChannel channel;
    private ReceiverAdapter receiverAdapter;

    @Before
    public void setUp() throws Exception {
        receiverAdapter = mock(ReceiverAdapter.class);

        channel = new JGroupsChannel();
    }

    @Test
    public void testConfiguration() throws Exception {
        String path = "udp.xml";

        channel.setConfiguration(new ClassPathResource(path));

        assertEquals(path, channel.getConfiguration().getFilename());
    }

    @Test
    public void testClusterName() throws Exception {
        String clusterName = "MyCluster";

        channel.setClusterName(clusterName);

        assertEquals(clusterName, channel.getClusterName());
    }

    @Test
    public void testConstruction() throws Exception {
        channel.setClusterName("ClusterName");
        channel.setConfiguration(new ClassPathResource("udp.xml"));
        channel.setReceiverAdapter(receiverAdapter);

        channel.postConstruct();

        JChannel jchannel = channel.getChannel();

        jchannel.send(null, new byte[]{0});

        assertEquals(channel.getClusterName(), jchannel.getClusterName());
        verify(receiverAdapter).receive((Message) argThat(new IsInstanceOf(Message.class)));
    }
}
