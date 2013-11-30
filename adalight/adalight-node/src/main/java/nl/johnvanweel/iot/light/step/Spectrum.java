package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.configuration.LightsConfiguration;
import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Loop through the spectrum
 */
public class Spectrum implements Steppable {
    private final ILightService ILightService;
    private final LightsConfiguration configuration;

    @Autowired
    public Spectrum(final LightsConfiguration configuration, final ILightService ILightService) {
        this.configuration = configuration;
        this.ILightService = ILightService;
    }

    private final double frequency = .1;

    private Direction direction = Direction.RIGHT;
    private int iteration = 0;

    @Override
    public void step() {
        iteration = direction.getNext(iteration);

        if (iteration >= 32 || iteration <= 0) {
            direction = Direction.change(direction);
        }

        int red = (int) (Math.sin(frequency * iteration + 0) * 127 + 128);
        int green = (int) (Math.sin(frequency * iteration + 2) * 127 + 128);
        int blue = (int) (Math.sin(frequency * iteration + 4) * 127 + 128);

        ILightService.allPixels(red, green, blue);
    }
}
