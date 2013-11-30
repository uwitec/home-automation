package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.controller.DaytimeController;
import nl.johnvanweel.iot.light.service.ILightService;
import nl.johnvanweel.iot.light.sunset.SunsetService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class DaylightDemo implements Steppable {
    private final DaytimeController daytimeController;

    @Autowired
    public DaylightDemo(final ILightService ILightService) {
        this.daytimeController = new DaytimeController(new SunsetService() {
            @Override
            public long getSunsetTime() {
                return System.currentTimeMillis();
            }
        }, ILightService);
        daytimeController.setTimeToDark(240_000);
    }

    @Override
    public void step() throws InterruptedException {
        for (int i = 0; i < 240; i++) {
            daytimeController.run();

            Thread.sleep(1000);
        }

    }
}
