package nl.johnvanweel.iot.light.step;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Any networked computer should have a Cylon on board.
 */
public class Cylon implements Steppable {
    private final ILightService ILightService;
    private int totalPixels;

    private int vlow = 5;
    private int low = 20;
    private int med = 50;
    private int high = 255;

    private int currentPixel = 0;
    private Direction direction = Direction.RIGHT;

    @Autowired
    public Cylon(final ILightService ILightService, @Qualifier("totalPixels") final int totalPixels) {
        this.ILightService = ILightService;
        this.totalPixels = totalPixels;
    }

    @Override
    public void step() {
        currentPixel = direction.getNext(currentPixel);

        if (currentPixel >= totalPixels-1|| currentPixel <= 0) {
            direction = Direction.change(direction);
        }


        ILightService.setPixel(currentPixel, 255, 0, 0);
        ILightService.send();
    }
}
