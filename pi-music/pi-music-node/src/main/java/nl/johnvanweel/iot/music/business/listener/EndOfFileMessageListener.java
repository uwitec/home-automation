package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.access.IMessageListener;
import nl.johnvanweel.iot.music.model.messaging.EndOfFileGroupMessage;
import nl.johnvanweel.iot.music.player.MusicPlayer;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class EndOfFileMessageListener implements IMessageListener {
    private final MusicPlayer player;

    @Autowired
    public EndOfFileMessageListener(final MusicPlayer player) {
        this.player = player;
    }

    @Override
    public boolean canHandle(final Message message) {
        return message.getObject() instanceof EndOfFileGroupMessage;
    }

    @Override
    public void handle(final Message message) {
        player.prepareEnd();
    }
}
