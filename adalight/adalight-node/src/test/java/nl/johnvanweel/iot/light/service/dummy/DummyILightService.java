package nl.johnvanweel.iot.light.service.dummy;

import nl.johnvanweel.iot.light.service.ILightService;
import org.apache.log4j.Logger;

/**
 *
 */
public class DummyILightService implements ILightService {
    private final Logger logger = Logger.getLogger(DummyILightService.class);

    @Override
    public void setPixel(int pixelNumber, int red, int green, int blue) {
        logger.info(String.format("Setting pixel [%s] to [%s,%s,%s].", pixelNumber, red, green, blue));
    }

    @Override
    public void allPixels(int red, int green, int blue) {
        logger.info(String.format("Setting all pixels to [%s,%s,%s].", red, green, blue));
    }

    @Override
    public void send() {
        logger.info("Sending pixels.");
    }
}
