package nl.johnvanweel.iot.light.controller;

import nl.johnvanweel.iot.light.service.ILightService;
import nl.johnvanweel.iot.light.sunset.SunsetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * This component checks the sunset time and adjusts the lights accordingly
 */
public class DaytimeController {
    private final Logger logger = Logger.getLogger(DaytimeController.class);

    private final SunsetService sunsetService;
    private final ILightService ILightService;

    private long timeToDark = 1_800_000;

    @Autowired
    public DaytimeController(final SunsetService sunsetService, final ILightService ILightService) {
        this.sunsetService = sunsetService;
        this.ILightService = ILightService;
    }

    /**
     * Check the sunset time once and calculate new illumination values
     */
    public void run() {
        logger.info("Running daytime job");
        long currentTime = System.currentTimeMillis();

        long timeAfterSunset = sunsetService.getSunsetTime();

        long diff = currentTime - timeAfterSunset;
        if (currentTime - timeAfterSunset > 0) {
            int darkPercentage = (int) ((100 * diff) / timeToDark);
            if (darkPercentage > 100) {
                darkPercentage = 100;
            }

            ILightService.allPixels(255 * darkPercentage / 100, 255 * darkPercentage / 100, 255 * darkPercentage / 100);
            ILightService.send();
        }
    }

    public long getTimeToDark() {
        return timeToDark;
    }

    public void setTimeToDark(long timeToDark) {
        this.timeToDark = timeToDark;
    }
}
