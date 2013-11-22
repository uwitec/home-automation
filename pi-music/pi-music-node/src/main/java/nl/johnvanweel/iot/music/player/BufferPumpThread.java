package nl.johnvanweel.iot.music.player;

import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.SortedSet;

/**
 * Thread that reads chunks of byte[] from the queue and pops them to the output stream.
 */
public class BufferPumpThread extends Thread {
    private final Logger logger = Logger.getLogger(BufferPumpThread.class);

    public static final long SLEEP_TIME = 10L;
    private boolean running = true;

    private SortedSet<ByteStreamAudioGroupMessage> buffer;
    private OutputStream outputStream;

    public BufferPumpThread(final SortedSet<ByteStreamAudioGroupMessage> buffer, final OutputStream outputStream) {
        this.buffer = buffer;
        this.outputStream = outputStream;
    }


    @Override
    public void run() {
        while (running) {
            final ByteStreamAudioGroupMessage entry;
            try {
                if (!buffer.isEmpty()) {
                    entry = buffer.first();

                    outputStream.write(entry.getBytes());
                    outputStream.flush();

                    buffer.remove(entry);
                } else {
                    Thread.sleep(SLEEP_TIME);
                    Thread.yield();
                }

            } catch (IOException | InterruptedException e) {
                logger.warn("Exception when moving bytes", e);
            }
        }
    }
}
