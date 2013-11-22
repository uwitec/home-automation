package nl.johnvanweel.iot.light.demo;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Loop through the spectrum, changing one light at the time
 */
public class FancySpectrum implements IDemo {
    private final ILightService ILightService;
    private int speed = 250;
    private int numberOfLights;

    @Autowired
    public FancySpectrum(final ILightService ILightService) {
        this.ILightService = ILightService;
    }

    @Override
    public void runRemo() throws InterruptedException {
        double frequency = .1;
        int oldRed = 0;
        int oldGreen = 0;
        int oldBlue = 0;

        for (int i = 0; i < 32; ++i) {
            int red = (int) (Math.sin(frequency * i + 0) * 127 + 128);
            int green = (int) (Math.sin(frequency * i + 2) * 127 + 128);
            int blue = (int) (Math.sin(frequency * i + 4) * 127 + 128);

            for (int currentPixel = 0; currentPixel < numberOfLights; currentPixel++) {
                for (int j = 0; j < numberOfLights; j++) {
                    if (currentPixel >= j) {
                        ILightService.setPixel(j, red, green, blue);
                    } else {
                        ILightService.setPixel(j, oldRed, oldGreen, oldBlue);
                    }
                }


                ILightService.setPixel(currentPixel, 255, 0, 0);

                ILightService.send();

                Thread.sleep(speed);
            }

            oldRed = red;
            oldGreen = green;
            oldBlue = blue;
        }
    }


    public int getNumberOfLights() {
        return numberOfLights;
    }

    public void setNumberOfLights(int numberOfLights) {
        this.numberOfLights = numberOfLights;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
