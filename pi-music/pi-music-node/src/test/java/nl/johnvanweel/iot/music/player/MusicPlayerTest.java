package nl.johnvanweel.iot.music.player;

import nl.johnvanweel.iot.music.access.JGroupsChannel;
import org.jgroups.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 *
 */
public class MusicPlayerTest {
    private MusicPlayer player;

    @Before
    public void setUp() throws Exception {
        player = new MusicPlayer(mock(JGroupsChannel.class));
    }

    @Test
    public void testPlayStartsNewBufferRequestThread() throws Exception {
        player.play("UUID", mock(Address.class));

        assertTrue(player.bufferRequestThread.isAlive());
    }
}
