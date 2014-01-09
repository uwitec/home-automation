package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.configuration.LightsConfiguration;
import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Loop through the spectrum, changing one light at the time
 */
public class FancySpectrum implements Steppable {
    private final ILightService lightService;
    private final LightsConfiguration configuration;

    private final double frequency = .1;

    @Autowired
    public FancySpectrum(final ILightService lightService, final LightsConfiguration configuration) {
        this.lightService = lightService;
        this.configuration = configuration;
    }

    private int oldRed = 0;
    private int oldGreen = 0;
    private int oldBlue = 0;

    private int iteration = 0;
    private int currentPixel = 0;

    @Override
    public void step() {
        iteration++;

        int red = (int) (Math.sin(frequency * iteration + 0) * 127 + 128);
        int green = (int) (Math.sin(frequency * iteration + 2) * 127 + 128);
        int blue = (int) (Math.sin(frequency * iteration + 4) * 127 + 128);

        if (currentPixel > configuration.getNumberOfLights()) {
            fixOldColors(red, green, blue);
        }

        setPixelColors(red, green, blue);
        currentPixel++;

        lightService.send();
    }

    private void setPixelColors(int red, int green, int blue) {
        for (int j = 0; j < configuration.getNumberOfLights(); j++) {
            if (currentPixel >= j) {
                lightService.setPixel(j, red, green, blue);
            } else {
                lightService.setPixel(j, oldRed, oldGreen, oldBlue);
            }
        }

        lightService.setPixel(currentPixel, 255, 0, 0);
    }

    private void fixOldColors(int red, int green, int blue) {
        currentPixel = 0;

        oldRed = red;
        oldGreen = green;
        oldBlue = blue;
    }
}
