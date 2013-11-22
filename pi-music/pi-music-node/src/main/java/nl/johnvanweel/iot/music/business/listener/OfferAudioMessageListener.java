package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.access.IMessageListener;
import nl.johnvanweel.iot.music.model.messaging.OfferAudioGroupMessage;
import nl.johnvanweel.iot.music.player.MusicPlayer;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * When a node in the network offers audio, this Listener will instruct the player to start streaming.
 */
public class OfferAudioMessageListener implements IMessageListener {
    private final MusicPlayer player;

    @Autowired
    public OfferAudioMessageListener(final MusicPlayer player) {
        this.player = player;
    }

    @Override
    public boolean canHandle(final Message message) {
        return message.getObject() instanceof OfferAudioGroupMessage;
    }

    @Override
    public void handle(final Message message) {
        // TODO: Check for multiple offers, we do not want to play them all!
        OfferAudioGroupMessage groupMessage = ((OfferAudioGroupMessage) message.getObject());

        player.play(groupMessage.getUUID(), message.getSrc());
    }
}
