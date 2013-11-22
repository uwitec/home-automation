package nl.johnvanweel.iot.music.business.listener;

import nl.johnvanweel.iot.music.access.IMessageListener;
import nl.johnvanweel.iot.music.access.JGroupsChannel;
import nl.johnvanweel.iot.music.access.scanning.MusicCollection;
import nl.johnvanweel.iot.music.access.scanning.MusicFile;
import nl.johnvanweel.iot.music.model.messaging.AudioBufferRequestGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.EndOfFileGroupMessage;
import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Locate a local File to stream and send a chunk to the requester.
 */
public class AudioBufferRequestMessageListener implements IMessageListener {
    private final Logger logger = Logger.getLogger(AudioBufferRequestMessageListener.class);

    public static final int BUFFER_SIZE = 50_000;
    private final MusicCollection musicCollection;
    private final JGroupsChannel channel;

    @Autowired
    public AudioBufferRequestMessageListener(final MusicCollection musicCollection, final JGroupsChannel channel) {
        this.musicCollection = musicCollection;
        this.channel = channel;
    }

    @Override
    public boolean canHandle(final Message message) {
        return message.getObject() instanceof AudioBufferRequestGroupMessage;
    }

    @Override
    public void handle(final Message message) {
        AudioBufferRequestGroupMessage groupMessage = ((AudioBufferRequestGroupMessage) message.getObject());

        MusicFile file = musicCollection.findMusicByUUID(groupMessage.getUuid());
        if (file == null) {
            logger.warn("Cannot find item with UUID " + groupMessage.getUuid());
            return;
        }

        InputStream in = getFileStream(file.getLocation());
        if (in == null) {
            logger.warn("Cannot open stream to " + file);
            return;
        }

        byte[] buffer = getChunk(groupMessage.getIteration(), in);

        ByteStreamAudioGroupMessage response = new ByteStreamAudioGroupMessage(groupMessage.getUuid(), groupMessage.getIteration(), buffer);
        channel.sendTo(message.getSrc(), response);

        if (buffer.length != BUFFER_SIZE) {
            EndOfFileGroupMessage endMessage = new EndOfFileGroupMessage(groupMessage.getUuid(), groupMessage.getIteration());
            channel.sendTo(message.getSrc(), endMessage);
        }
    }

    private InputStream getFileStream(Path file) {
        final InputStream in;

        try {
            in = new FileInputStream(file.toFile());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        return in;
    }

    private byte[] getChunk(int iteration, InputStream in) {
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;

        try {
            in.skip(iteration * BUFFER_SIZE);
            read = in.read(buffer);

            if (read == -1) {
                return new byte[0];
            }
            logger.debug("Read " + read + " bytes from stream");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new byte[0];
        }

        if (read == BUFFER_SIZE) {
            return buffer;
        } else {
            return Arrays.copyOf(buffer, read);
        }
    }
}
