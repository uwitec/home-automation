package nl.johnvanweel.iot.music.player.buffer;

import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ByteStreamIterationComparatorTest {
    private ByteStreamIterationComparator comparator;

    @Before
    public void setUp() throws Exception {
        comparator = new ByteStreamIterationComparator();
    }

    @Test
    public void testComparation() throws Exception {
        ByteStreamAudioGroupMessage message1 = new ByteStreamAudioGroupMessage("UUID", 1, new byte[0]);
        ByteStreamAudioGroupMessage message2 = new ByteStreamAudioGroupMessage("UUID", 2, new byte[0]);

        assertEquals(comparator.compare(message1, message2), -1);
    }
}
