package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.access.IChannel;
import nl.johnvanweel.iot.music.access.IMessageListener;
import nl.johnvanweel.iot.music.access.scanning.MusicCollection;
import nl.johnvanweel.iot.music.access.scanning.MusicFile;
import nl.johnvanweel.iot.music.model.messaging.OfferAudioGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.QueryAudioGroupMessage;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Listens for queries.
 * Attempts to find the item in the collection and return an Offer to the requester with the item UUID.
 */
public class QueryAudioMessageListener implements IMessageListener {
    private final MusicCollection musicCollection;
    private final IChannel channel;

    @Autowired
    public QueryAudioMessageListener(final MusicCollection musicCollection, final IChannel channel) {
        this.musicCollection = musicCollection;
        this.channel = channel;
    }

    @Override
    public boolean canHandle(Message message) {
        return message.getObject() instanceof QueryAudioGroupMessage;
    }

    @Override
    public void handle(Message message) {
        QueryAudioGroupMessage groupMessage = ((QueryAudioGroupMessage) message.getObject());

        MusicFile response = musicCollection.findOne(groupMessage.getAudioQuery());

        if (response != null) {
            OfferAudioGroupMessage offerMessage = new OfferAudioGroupMessage(response.getUuid().toString());

            channel.sendTo(message.getSrc(), offerMessage);
        }
    }
}
