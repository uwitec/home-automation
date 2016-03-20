package nl.johnvanweel.iot.light.runmode.filter;

/**
 * Created by john on 3/20/16.
 */
public abstract class AbstractLightFilter implements LightFilter {
    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
