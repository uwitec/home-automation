package nl.johnvanweel.iot.music.player;

import nl.johnvanweel.iot.music.access.JGroupsChannel;
import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import nl.johnvanweel.iot.music.player.buffer.BufferPrinter;
import nl.johnvanweel.iot.music.player.buffer.BufferRequestThread;
import nl.johnvanweel.iot.music.player.buffer.ByteStreamIterationComparator;
import org.jgroups.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Main Music Player API.
 */
public class MusicPlayer {
    private final JGroupsChannel channel;

    private final SortedSet<ByteStreamAudioGroupMessage> buffer = new TreeSet<>(new ByteStreamIterationComparator());

    BufferRequestThread bufferRequestThread;
    JLayerPlayer playerThread;
    Thread bufferPrintThread;
    BufferPumpThread bufferPumpThread;

    private PipedOutputStream outputStream;
    private PipedInputStream inputStream;

    @Autowired
    public MusicPlayer(JGroupsChannel channel) throws IOException {
        this.channel = channel;

        outputStream = new PipedOutputStream();
        inputStream = new PipedInputStream(outputStream);

        startBufferPrintThread();
        startPlayerThread();
        startBufferPumpThread();
    }

    private void startBufferPrintThread() {
        bufferPrintThread = new Thread(new BufferPrinter(buffer));
        bufferPrintThread.start();
    }

    /**
     * Plays and item with the given UUID from an Address.
     *
     * @param fileUuid    UUID of the remote file
     * @param nodeAddress Address where the file is located
     */
    public void play(String fileUuid, Address nodeAddress) {
        startBufferRequestThread(fileUuid, nodeAddress);
    }

    /**
     * Stops playing the current item, as soon as the item is done.
     */
    public void prepareEnd() {
        bufferRequestThread.cleanStop();
    }

    private void startPlayerThread() {
        playerThread = new JLayerPlayer(inputStream);
        playerThread.start();
    }

    private void startBufferPumpThread() {
        bufferPumpThread = new BufferPumpThread(buffer, outputStream);
        bufferPumpThread.start();
    }

    private void startBufferRequestThread(String fileUuid, Address nodeAddress) {
        bufferRequestThread = new BufferRequestThread(buffer, fileUuid, nodeAddress, channel);
        bufferRequestThread.start();
    }

    /**
     * Appends a stream of bytes to the play buffer.
     *
     * @param message
     */
    public void appendBuffer(ByteStreamAudioGroupMessage message) {
        buffer.add(message);
    }
}
