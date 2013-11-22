package nl.johnvanweel.iot.music.model.messaging;

/**
 * User: John van Weel
 * Date: 1/10/12
 * Time: 6:49 PM
 */
public class AudioBufferRequestGroupMessage extends GroupMessage {
    private final String uuid;
    private final int iteration;

    public AudioBufferRequestGroupMessage(String uuid, int iteration) {
        this.uuid = uuid;
        this.iteration = iteration;
    }

    public String getUuid() {
        return uuid;
    }

    public int getIteration() {
        return iteration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioBufferRequestGroupMessage message = (AudioBufferRequestGroupMessage) o;

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
        return "AudioBufferRequestGroupMessage{" +
                "uuid='" + uuid + '\'' +
                ", iteration=" + iteration +
                '}';
    }
}




