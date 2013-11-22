package nl.johnvanweel.iot.music.model.messaging;

/**
 * User: John van Weel
 * Date: 1/10/12
 * Time: 6:49 PM
 */
public class ByteStreamAudioGroupMessage extends GroupMessage {
    private final String uuid;
    private final byte[] bytes;
    private final int iteration;

    public ByteStreamAudioGroupMessage(String uuid, int iteration, byte[] bytes) {
        this.uuid = uuid;
        this.iteration = iteration;
        this.bytes = bytes;
    }

    public String getUuid() {
        return uuid;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getIteration() {
        return iteration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByteStreamAudioGroupMessage message = (ByteStreamAudioGroupMessage) o;

        if (iteration != message.iteration) return false;
        if (uuid != null ? !uuid.equals(message.uuid) : message.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + iteration;
        return result;
    }

    @Override
    public String toString() {
        return "ByteStreamAudioGroupMessage{" +
                "uuid='" + uuid + '\'' +
                ", iteration=" + iteration +
                '}';
    }
}




