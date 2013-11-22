package nl.johnvanweel.iot.music.model.messaging;

/**
 * User: John van Weel
 * Date: 1/10/12
 * Time: 6:49 PM
 */
public class OfferAudioGroupMessage extends GroupMessage {
    private String uuid;

    public OfferAudioGroupMessage(String uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OfferAudioGroupMessage{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
