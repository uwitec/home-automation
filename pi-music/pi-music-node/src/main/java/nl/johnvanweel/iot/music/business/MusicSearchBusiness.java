package nl.johnvanweel.iot.music.business;

import nl.johnvanweel.iot.music.access.IChannel;
import nl.johnvanweel.iot.music.model.messaging.QueryAudioGroupMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class MusicSearchBusiness {
    private final IChannel channel;

    @Autowired
    public MusicSearchBusiness(final IChannel channel) {
        this.channel = channel;
    }

    public void searchCluster(String query) {
        channel.broadcast(new QueryAudioGroupMessage(query));
    }


}
