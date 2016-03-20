package nl.johnvanweel.iot.light.runmode.step;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Loop through the spectrum
 */
public class Spectrum implements Steppable {
    private final ILightService ILightService;
    private final double frequency = .1;
    private int totalPixels;
    private Direction direction = Direction.RIGHT;
    private int iteration = 0;
    @Autowired
    public Spectrum(final ILightService ILightService, @Qualifier("totalPixels") final int totalPixels) {
        this.ILightService = ILightService;
        this.totalPixels = totalPixels;
    }

    @Override
    public void step() {
        iteration = direction.getNext(iteration);

        if (iteration >= totalPixels - 1 || iteration <= 0) {
            direction = Direction.change(direction);
        }

        int red = (int) (Math.sin(frequency * iteration + 0) * 127 + 128);
        int green = (int) (Math.sin(frequency * iteration + 2) * 127 + 128);
        int blue = (int) (Math.sin(frequency * iteration + 4) * 127 + 128);

        ILightService.allPixels(red, green, blue);
    }
}
