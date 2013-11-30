package nl.johnvanweel.iot.light.access.boblight;

import com.sun.jna.Pointer;
import nl.johnvanweel.iot.light.service.ILightService;
import nl.johnvanweel.iot.light.service.LightException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Implementation using the boblight daemon running on the default address.
 */
public class BobILightService implements ILightService {
    public static final int ALL_PIXELS = -1;

    private BoblightDaemon daemon;
    private Pointer bob;

    @Autowired
    public BobILightService(final BoblightDeamonFactory factory) {
        this.daemon = factory.getInstance();

        bob = daemon.boblight_init();
        int resultCode = daemon.boblight_connect(bob, null, -1, 5000000);
        int prioResult = daemon.boblight_setpriority(bob, 128);

        if (resultCode == 0 || prioResult == 0) {
            throw new LightException("Cannot connect to boblightd.");
        }
    }

    @Override
    public void setPixel(int pixelNumber, int red, int green, int blue) {
        if (pixelNumber >= 0) {
            daemon.boblight_addpixel(bob, pixelNumber, new int[]{red, green, blue});
        }
    }

    @Override
    public void allPixels(int red, int green, int blue) {
        daemon.boblight_addpixel(bob, ALL_PIXELS, new int[]{red, green, blue});
        send();
    }

    @Override
    public void send() {
        daemon.boblight_sendrgb(bob, 1, new int[]{255});
    }
}
