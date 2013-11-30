package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Any networked computer should have a Cylon on board.
 */
public class Cylon implements Steppable {
    private final ILightService ILightService;

    @Autowired
    public Cylon(final ILightService ILightService) {
        this.ILightService = ILightService;
    }

    @Override
    public void step() throws InterruptedException {
        int vlow = 5;
        int low = 20;
        int med = 50;
        int high = 255;

        for (int i = 0; i < 8; i++) {
            for (int currentPixel = 0; currentPixel < 20; currentPixel++) {
                for (int j = 0; j < 20; j++) {
                    if (currentPixel == j) {
                        if (currentPixel > 2) {
                            ILightService.setPixel(currentPixel - 3, vlow, 0, 0);
                            ILightService.setPixel(currentPixel - 2, low, 0, 0);
                            ILightService.setPixel(currentPixel - 1, med, 0, 0);
                        }

                        ILightService.setPixel(currentPixel, high, 0, 0);
                        ILightService.setPixel(currentPixel + 1, med, 0, 0);
                        ILightService.setPixel(currentPixel + 2, low, 0, 0);
                        ILightService.setPixel(currentPixel + 3, vlow, 0, 0);
                    } else {
                        ILightService.setPixel(j, 0, 0, 0);
                    }
                }
                ILightService.send();
                Thread.sleep(25);
            }

            for (int currentPixel = 20; currentPixel > 0; currentPixel--) {
                for (int j = 20; j > 0; j--) {
                    if (currentPixel == j) {
                        if (currentPixel > 2) {
                            ILightService.setPixel(currentPixel - 3, vlow, 0, 0);
                            ILightService.setPixel(currentPixel - 2, low, 0, 0);
                            ILightService.setPixel(currentPixel - 1, med, 0, 0);
                        }

                        ILightService.setPixel(currentPixel, high, 0, 0);
                        ILightService.setPixel(currentPixel + 1, med, 0, 0);
                        ILightService.setPixel(currentPixel + 2, low, 0, 0);
                        ILightService.setPixel(currentPixel + 3, vlow, 0, 0);
                    } else {
                        ILightService.setPixel(j, 0, 0, 0);
                    }
                }
                ILightService.send();
                Thread.sleep(25);
            }
        }
    }
}
