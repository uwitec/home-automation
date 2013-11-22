package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.access.IMessageListener;
import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import nl.johnvanweel.iot.music.player.MusicPlayer;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class ByteStreamMessageListener implements IMessageListener {
    private final MusicPlayer player;

    @Autowired
    public ByteStreamMessageListener(final MusicPlayer player) {
        this.player = player;
    }

    @Override
    public boolean canHandle(final Message message) {
        return message.getObject() instanceof ByteStreamAudioGroupMessage;
    }

    @Override
    public void handle(final Message message) {
        ByteStreamAudioGroupMessage groupMessage = ((ByteStreamAudioGroupMessage) message.getObject());

        player.appendBuffer(groupMessage);
    }
}
