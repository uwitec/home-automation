package nl.johnvanweel.iot.light.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 3/20/16.
 */
public class LightRunMode implements Serializable{
    private String name;
    private List<String> filters;

    public LightRunMode() {
        this(null, new ArrayList<>());
    }

    public LightRunMode(String name) {
        this(name, new ArrayList<>());
    }

    public LightRunMode(String name, List<String> filters) {
        this.name = name;
        this.filters = filters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFilters() {
        return filters;
    }
}
