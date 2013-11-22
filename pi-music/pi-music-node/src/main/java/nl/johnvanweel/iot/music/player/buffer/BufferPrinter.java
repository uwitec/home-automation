package nl.johnvanweel.iot.music.player.buffer;

import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

/**
 *
 */
public class BufferPrinter implements Runnable {
    public static final long SLEEP_TIME = 750L;
    private final Logger logger = Logger.getLogger(BufferPrinter.class);

    private final SortedSet<ByteStreamAudioGroupMessage> buffer;

    public BufferPrinter(final SortedSet<ByteStreamAudioGroupMessage> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            StringBuilder sb = new StringBuilder("<");
            List<ByteStreamAudioGroupMessage> copy = new ArrayList<>(buffer);
            for (ByteStreamAudioGroupMessage message : copy) {
                sb.append(message.getIteration()).append(" ");
            }

            sb.append(">");
            logger.debug(sb.toString());

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
