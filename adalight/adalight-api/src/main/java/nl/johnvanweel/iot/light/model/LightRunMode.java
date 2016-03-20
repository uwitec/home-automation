package nl.johnvanweel.iot.light.model;

import java.io.Serializable;

/**
 * Created by john on 3/20/16.
 */
public class LightRunMode implements Serializable{
    private String name;

    public LightRunMode() {
    }

    public LightRunMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
