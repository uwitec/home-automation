package nl.johnvanweel.iot.music.business;

import nl.johnvanweel.iot.music.access.IChannel;
import nl.johnvanweel.iot.music.access.scanning.MusicCollection;
import nl.johnvanweel.iot.music.model.messaging.GroupMessage;
import nl.johnvanweel.iot.music.model.messaging.QueryAudioGroupMessage;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class MusicSearchBusinessTest {
    private MusicSearchBusiness business;

    private IChannel channel;
    private MusicCollection collection;

    @Before
    public void setUp() throws Exception {
        channel = mock(IChannel.class);
        collection = mock(MusicCollection.class);
        business = new MusicSearchBusiness(channel);
    }

    @Test
    public void testSearch() throws Exception {
        final String query = "MY_QUERY";

        business.searchCluster(query);

        verify(channel).broadcast(argThat(new nl.johnvanweel.iot.music.SimpleBaseMatcher<GroupMessage>() {
            @Override
            public boolean matches(Object o) {
                return o instanceof QueryAudioGroupMessage && ((QueryAudioGroupMessage) o).getAudioQuery().equals(query);
            }
        }));
    }
}
