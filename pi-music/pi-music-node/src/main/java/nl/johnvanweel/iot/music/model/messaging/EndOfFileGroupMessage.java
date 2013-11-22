package nl.johnvanweel.iot.music.model.messaging;

/**
 * User: John van Weel
 * Date: 1/10/12
 * Time: 6:49 PM
 */
public class EndOfFileGroupMessage extends GroupMessage {
    private final String uuid;
    private final int iteration;

    public EndOfFileGroupMessage(String uuid, int iteration) {
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("EndOfFileGroupMessage{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", iteration=").append(iteration);
        sb.append('}');
        return sb.toString();
    }
}




