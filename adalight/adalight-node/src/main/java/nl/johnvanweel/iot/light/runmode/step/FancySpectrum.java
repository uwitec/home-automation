package nl.johnvanweel.iot.light.runmode.step;

import nl.johnvanweel.iot.light.configuration.LightsConfiguration;
import nl.johnvanweel.iot.light.runmode.filter.FilteredLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Loop through the spectrum, changing one light at the time
 */
public class FancySpectrum implements Steppable {
    private final FilteredLightService filteredLightService;
    private final LightsConfiguration configuration;
    private final int totalPixels;

    private final double frequency = .1;
    private int oldRed = 0;
    private int oldGreen = 0;
    private int oldBlue = 0;
    private int iteration = 0;
    private int currentPixel = 0;

    @Autowired
    public FancySpectrum(final FilteredLightService filteredLightService, final LightsConfiguration configuration, @Qualifier("totalPixels") final int totalPixels) {
        this.filteredLightService = filteredLightService;
        this.configuration = configuration;
        this.totalPixels = totalPixels;
    }

    @Override
    public void step() {
        iteration++;

        int red = (int) (Math.sin(frequency * iteration + 0) * 127 + 128);
        int green = (int) (Math.sin(frequency * iteration + 2) * 127 + 128);
        int blue = (int) (Math.sin(frequency * iteration + 4) * 127 + 128);

        if (currentPixel > totalPixels - 1) {
            fixOldColors(red, green, blue);
        }

        setPixelColors(red, green, blue);
        currentPixel++;

        filteredLightService.send();
    }

    private void setPixelColors(int red, int green, int blue) {
        for (int j = 0; j < totalPixels; j++) {
            if (currentPixel >= j) {
                filteredLightService.setPixel(j, red, green, blue);
            } else {
                filteredLightService.setPixel(j, oldRed, oldGreen, oldBlue);
            }
        }

        filteredLightService.setPixel(currentPixel, 255, 0, 0);
    }

    private void fixOldColors(int red, int green, int blue) {
        currentPixel = 0;

        oldRed = red;
        oldGreen = green;
        oldBlue = blue;
    }

    public void toggleFilter(String name) {
        filteredLightService.toggleFilter(name);
    }

    public List<String> getFilters() {
        return filteredLightService.getLightFilterNames();
    }
}
