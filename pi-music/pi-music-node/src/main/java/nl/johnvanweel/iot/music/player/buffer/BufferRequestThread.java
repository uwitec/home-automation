package nl.johnvanweel.iot.music.player.buffer;

import nl.johnvanweel.iot.music.access.JGroupsChannel;
import nl.johnvanweel.iot.music.model.messaging.AudioBufferRequestGroupMessage;
import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import org.apache.log4j.Logger;
import org.jgroups.Address;

import java.util.SortedSet;

/**
 * This Thread should be started after an item has been negotiated.
 * Provide the buffer to fill, the item UUID, address where the data is located and this Thread will keep the buffer filled.
 */
public class BufferRequestThread extends Thread {
    private Logger logger = Logger.getLogger(BufferRequestThread.class);

    public static final Integer BUFFER_SIZE = 15;
    public static final Long SLEEP_DURATION = 250L;

    private final SortedSet<ByteStreamAudioGroupMessage> buffer;
    private final String uuid;
    private final Address source;
    private final JGroupsChannel channel;

    private int lastRequestedIteration = -1;
    private boolean running = true;

    /**
     * @param buffer  Buffer to fill
     * @param uuid    UUID of the item to stream
     * @param source  Address where the item is located
     * @param channel A reference to the broadcast channel
     */
    public BufferRequestThread(final SortedSet<ByteStreamAudioGroupMessage> buffer, final String uuid, final Address source, final JGroupsChannel channel) {
        this.buffer = buffer;
        this.uuid = uuid;
        this.source = source;
        this.channel = channel;
    }

    @Override
    public void run() {
        while (running) {
            if (buffer.size() < BUFFER_SIZE) {
                retrieveNewIteration();
            }

            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                logger.error("Interrupted.", e);
            }
        }
    }

    /**
     * Terminate the Thread
     */
    public void cleanStop() {
        running = false;
    }

    private void retrieveNewIteration() {
        final int iteration = findCurrentIteration();

        if (lastRequestedIteration < iteration) {
            try {
                requestNewChunk(iteration);

                lastRequestedIteration = iteration;

                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                logger.error("Interrupted.", e);
            }
        }
    }

    private void requestNewChunk(int iteration) {
        AudioBufferRequestGroupMessage request = new AudioBufferRequestGroupMessage(uuid, iteration);
        channel.sendTo(source, request);
    }

    private int findCurrentIteration() {
        final int iteration;
        if (!buffer.isEmpty()) {
            iteration = buffer.last().getIteration() + 1;
        } else if (lastRequestedIteration != -1) {
            iteration = lastRequestedIteration + 1;
        } else {
            iteration = 0;
        }
        return iteration;
    }
}
