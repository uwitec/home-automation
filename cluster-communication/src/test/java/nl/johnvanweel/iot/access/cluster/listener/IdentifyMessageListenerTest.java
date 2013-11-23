package nl.johnvanweel.iot.access.cluster.listener;

import nl.johnvanweel.iot.access.cluster.JGroupsChannel;
import nl.johnvanweel.iot.access.cluster.message.GroupMessage;
import nl.johnvanweel.iot.access.cluster.message.IdentifyGroupMessage;
import nl.johnvanweel.iot.access.cluster.message.IdentifyResponseMessage;
import org.hamcrest.core.IsInstanceOf;
import org.jgroups.Address;
import org.jgroups.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class IdentifyMessageListenerTest {
    private IdentifyMessageListener listener;

    private IMessageListener[] messageListeners;

    private JGroupsChannel channel;

    @Before
    public void setUp() throws Exception {
        channel = mock(JGroupsChannel.class);
        listener = new IdentifyMessageListener(channel);

        messageListeners = new IMessageListener[]{mock(IMessageListener.class)};

        listener.setAllMessageListeners(listener);
    }

    @Test
    public void testCanHandle() throws Exception {
        Message message = new Message();
        message.setObject(new IdentifyGroupMessage());

        assertTrue(listener.canHandle(message));
    }

    @Test
    public void testCannotHandle() throws Exception {
        Message message = new Message();
        message.setObject(mock(GroupMessage.class));

        assertFalse(listener.canHandle(message));
    }

    @Test
    public void testHandle() throws Exception {
        Address replyAddress = mock(Address.class);
        Message message = new Message();
        message.setObject(new IdentifyGroupMessage());
        message.setSrc(replyAddress);

        listener.handle(message);

        verify(channel).sendTo(eq(replyAddress), (GroupMessage) argThat(new IsInstanceOf(IdentifyResponseMessage.class)));
    }
}
