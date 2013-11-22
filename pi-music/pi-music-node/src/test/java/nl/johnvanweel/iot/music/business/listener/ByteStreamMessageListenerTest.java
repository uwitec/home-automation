package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import nl.johnvanweel.iot.music.player.MusicPlayer;
import org.jgroups.Message;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class ByteStreamMessageListenerTest {
    private ByteStreamMessageListener listener;

    private MusicPlayer musicPlayer;

    @Before
    public void setUp() throws Exception {
        musicPlayer = mock(MusicPlayer.class);

        listener = new ByteStreamMessageListener(musicPlayer);
    }

    @Test
    public void testAppendsToPlayerBuffer() throws Exception {
        ByteStreamAudioGroupMessage message = new ByteStreamAudioGroupMessage("UUID", 1, new byte[0]);
        Message wrapper = new Message();
        wrapper.setObject(message);

        listener.handle(wrapper);

        verify(musicPlayer).appendBuffer(message);
    }
}
