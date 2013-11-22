package nl.johnvanweel.iot.light.demo;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Loop through the spectrum
 */
public class Spectrum implements IDemo {
    private final ILightService ILightService;
    private int speed = 100;

    @Autowired
    public Spectrum(final ILightService ILightService) {
        this.ILightService = ILightService;
    }

    @Override
    public void runRemo() throws InterruptedException {
        double frequency = .1;
        for (int i = 0; i < 32; ++i) {
            int red = (int) (Math.sin(frequency * i + 0) * 127 + 128);
            int green = (int) (Math.sin(frequency * i + 2) * 127 + 128);
            int blue = (int) (Math.sin(frequency * i + 4) * 127 + 128);

            ILightService.allPixels(red, green, blue);
            ILightService.send();

            Thread.sleep(speed);
        }

        for (int i = 32; i > 0; --i) {
            int red = (int) (Math.sin(frequency * i + 0) * 127 + 128);
            int green = (int) (Math.sin(frequency * i + 2) * 127 + 128);
            int blue = (int) (Math.sin(frequency * i + 4) * 127 + 128);

            ILightService.allPixels(red, green, blue);
            ILightService.send();

            Thread.sleep(speed);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
