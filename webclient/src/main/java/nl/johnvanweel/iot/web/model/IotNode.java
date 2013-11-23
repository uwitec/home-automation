package nl.johnvanweel.iot.web.model;

/**
 *
 */
public class IotNode {
    private String name;
    private String[] capabilities;

    public IotNode(String name, String... capabilities) {
        this.name = name;
        this.capabilities = capabilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String[] capabilities) {
        this.capabilities = capabilities;
    }
}
