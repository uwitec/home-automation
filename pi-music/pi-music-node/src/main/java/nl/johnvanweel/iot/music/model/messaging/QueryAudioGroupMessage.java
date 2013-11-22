package nl.johnvanweel.iot.music.model.messaging;

/**
 * User: John van Weel
 * Date: 1/10/12
 * Time: 6:49 PM
 */
public class QueryAudioGroupMessage extends GroupMessage {
    private final String query;


    public QueryAudioGroupMessage(String query) {
        this.query = query;
    }

    public String getAudioQuery() {
        return query;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryAudioGroupMessage{");
        sb.append("query='").append(query).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
