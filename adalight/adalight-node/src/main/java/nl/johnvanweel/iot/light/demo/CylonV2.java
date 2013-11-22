package nl.johnvanweel.iot.light.demo;

import nl.johnvanweel.iot.light.service.ILightService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Any networked computer should have a Cylon on board.
 */
public class CylonV2 implements IDemo {
    private final ILightService ILightService;
    private int speed = 100;
    private int amountOfPixels = 20;

    @Autowired
    public CylonV2(final ILightService ILightService) {
        this.ILightService = ILightService;
    }

    @Override
    public void runRemo() throws InterruptedException {
        int currentTotalLight = 100;
        int totalLight = 100;
        int currentPixel = 0;
        Direction direction = Direction.RIGHT;

        for (int i = 0; i < 10000; i++) {
            if (currentTotalLight >= 100) {
                if (direction == Direction.RIGHT) {
                    currentPixel++;
                } else {
                    currentPixel--;
                }
                currentTotalLight = 0;
            }

            direction = determineDirection(currentPixel, direction);

            int prevPixel = determineDimmingPixel(currentPixel, direction);

            int step = 10;
            currentTotalLight += step;

            ILightService.setPixel(currentPixel, currentTotalLight, 0, 0);
            ILightService.setPixel(prevPixel, totalLight - currentTotalLight, 0, 0);
            ILightService.send();

            Thread.sleep(1);
        }
    }

    private Direction determineDirection(int currentPixel, Direction currentDirection) {
        if (currentPixel > 20) {
            currentDirection = Direction.LEFT;
        } else if (currentPixel < 0) {
            currentDirection = Direction.RIGHT;
        }
        return currentDirection;
    }

    private int determineDimmingPixel(int currentPixel, Direction direction) {
        int prevPixel;
        if (direction == Direction.LEFT) {
            prevPixel = currentPixel + 1;
        } else {
            prevPixel = currentPixel - 1;
        }
        return prevPixel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private enum Direction {
        LEFT, RIGHT;
    }
}
