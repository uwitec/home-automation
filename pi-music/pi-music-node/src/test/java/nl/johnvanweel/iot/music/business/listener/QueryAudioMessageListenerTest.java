package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.SimpleBaseMatcher;
import nl.johnvanweel.iot.music.access.JGroupsChannel;
import nl.johnvanweel.iot.music.access.scanning.MusicCollection;
import nl.johnvanweel.iot.music.access.scanning.MusicFile;
import nl.johnvanweel.iot.music.model.messaging.GroupMessage;
import nl.johnvanweel.iot.music.model.messaging.OfferAudioGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.QueryAudioGroupMessage;
import org.jgroups.Address;
import org.jgroups.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 *
 */
public class QueryAudioMessageListenerTest {
    private QueryAudioMessageListener listener;

    private MusicCollection musicCollection;
    private JGroupsChannel channel;

    @Before
    public void setUp() throws Exception {
        musicCollection = mock(MusicCollection.class);
        channel = mock(JGroupsChannel.class);
        listener = new QueryAudioMessageListener(musicCollection, channel);
    }

    @Test
    public void testCanHandle() throws Exception {
        Message message = new Message();
        message.setObject(new QueryAudioGroupMessage(null));

        assertTrue(listener.canHandle(message));
    }

    @Test
    public void testCanNotHandle() throws Exception {
        Message message = new Message();
        message.setObject("Not a valid message object");

        assertFalse(listener.canHandle(message));
    }

    @Test
    public void testQuery() throws Exception {
        Message message = new Message();
        message.setSrc(mock(Address.class));
        message.setObject(new QueryAudioGroupMessage("Q"));

        MusicFile musicFile = new MusicFile(null, null, null, null);
        when(musicCollection.findOne("Q")).thenReturn(musicFile);

        listener.handle(message);

        verify(channel).sendTo(eq(message.getSrc()), argThat(new SimpleBaseMatcher<GroupMessage>() {
            @Override
            public boolean matches(Object o) {
                return ((OfferAudioGroupMessage) o).getUUID().equals(musicFile.getUuid().toString());
            }
        }));
    }
}
