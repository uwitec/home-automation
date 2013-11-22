package nl.johnvanweel.iot.music.player.buffer;

import nl.johnvanweel.iot.music.access.JGroupsChannel;
import nl.johnvanweel.iot.music.model.messaging.AudioBufferRequestGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.GroupMessage;
import nl.johnvanweel.iot.music.model.messaging.OfferAudioGroupMessage;
import org.hamcrest.core.IsInstanceOf;
import org.jgroups.Address;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class BufferRequestThreadTest {
    private BufferRequestThread thread;
    private SortedSet<ByteStreamAudioGroupMessage> buffer;
    private OfferAudioGroupMessage currentMessage;
    private Address source;
    private JGroupsChannel channel;

    @Before
    public void setUp() throws Exception {
        buffer = new TreeSet<>(new ByteStreamIterationComparator());
        source = mock(Address.class);
        channel = mock(JGroupsChannel.class);

        thread = new BufferRequestThread(buffer, "UUID", source, channel);
    }

    @Test
    public void testBufferingFirstIteration() throws Exception {
        new Thread(thread).start();

        Thread.sleep(100L);

        verify(channel, VerificationModeFactory.times(1)).sendTo(eq(source), (GroupMessage) argThat(new IsInstanceOf(AudioBufferRequestGroupMessage.class)));
    }

    @Test
    public void testBufferingNextIteration() throws Exception {
        new Thread(thread).start();

        Thread.sleep(100L);

        buffer.add(new ByteStreamAudioGroupMessage("UUID", 2, new byte[0]));

        // TODO: Must be a better way...
        Thread.sleep(2000L);

        verify(channel, VerificationModeFactory.times(2)).sendTo(eq(source), (GroupMessage) argThat(new IsInstanceOf(AudioBufferRequestGroupMessage.class)));
    }
}
