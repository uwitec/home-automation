package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.model.messaging.OfferAudioGroupMessage;
import nl.johnvanweel.iot.music.player.MusicPlayer;
import org.jgroups.Address;
import org.jgroups.Message;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class OfferAudioMessageListenerTest {
    private MusicPlayer player;
    private OfferAudioMessageListener listener;

    @Before
    public void setUp() throws Exception {
        player = mock(MusicPlayer.class);
        listener = new OfferAudioMessageListener(player);
    }

    @Test
    public void testStartPlaying() throws Exception {
        OfferAudioGroupMessage message = new OfferAudioGroupMessage("MY_UUID");

        Message messageWrapper = new Message();
        messageWrapper.setObject(message);
        messageWrapper.setSrc(mock(Address.class));

        listener.handle(messageWrapper);

        verify(player).play(eq(message.getUUID()), eq(messageWrapper.getSrc()));
    }
}
