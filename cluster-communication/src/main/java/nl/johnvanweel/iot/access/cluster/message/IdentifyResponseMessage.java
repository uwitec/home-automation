package nl.johnvanweel.iot.access.cluster.message;

/**
 *
 */
public class IdentifyResponseMessage extends GroupMessage {
    private String host;
    private String[] capabilities;

    public IdentifyResponseMessage(String host, String... capabilities) {
        this.host = host;
        this.capabilities = capabilities;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String[] capabilities) {
        this.capabilities = capabilities;
    }
}
