package nl.johnvanweel.iot.light.sunset;

import javax.annotation.PostConstruct;

/**
 *
 */
public class DummySunsetService implements SunsetService {
    private long now;

    @PostConstruct
    public void postConstruct() {
        this.now = System.currentTimeMillis();
    }

    @Override
    public long getSunsetTime() {
        return now;
    }
}
