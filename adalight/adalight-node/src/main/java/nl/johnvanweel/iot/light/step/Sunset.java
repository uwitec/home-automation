package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.service.ILightService;
import nl.johnvanweel.iot.light.sunset.SunState;
import nl.johnvanweel.iot.light.sunset.SunsetService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class Sunset implements Steppable {
    private final SunsetService sunsetService;
    private final ILightService lightService;

    @Autowired
    public Sunset(final SunsetService sunsetService, final ILightService lightService) {
        this.sunsetService = sunsetService;
        this.lightService = lightService;
    }


    @Override
    public void step() {
        SunState state = sunsetService.getSunState();
        switch (state) {
            case UP:
                lightService.allPixels(0, 0, 0);
                break;
            case DOWN:
                lightService.allPixels(255, 255, 255);
                break;
            case RISING:
            case SETTING:
                int newValue = calculateNewValue(state);

                lightService.allPixels(newValue, newValue, newValue);

                break;
        }
    }

    private int calculateNewValue(SunState state) {
        double percentage = sunsetService.getTransitionPercentage();

        int settingValue = (int) Math.round((percentage / 100) * 255);

        if (state == SunState.RISING) {
            settingValue = 255 - settingValue;
        }

        return settingValue;
    }
}
