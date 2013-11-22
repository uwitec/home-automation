package nl.johnvanweel.iot.music.player.buffer;

import nl.johnvanweel.iot.music.model.messaging.ByteStreamAudioGroupMessage;

import java.util.Comparator;

/**
 *
 */
public class ByteStreamIterationComparator implements Comparator<ByteStreamAudioGroupMessage> {
    @Override
    public int compare(ByteStreamAudioGroupMessage o1, ByteStreamAudioGroupMessage o2) {
        return Integer.valueOf(o1.getIteration()).compareTo(o2.getIteration());
    }
}
