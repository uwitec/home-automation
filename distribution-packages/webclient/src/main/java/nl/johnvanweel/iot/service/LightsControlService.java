package nl.johnvanweel.iot.service;

import nl.johnvanweel.iot.light.api.LightsBusiness;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This service sends a Lights-change message to the Light-capable device.
 */
@Component
public class LightsControlService {
	private final LightsBusiness lightsBusiness;

    @Autowired
    public LightsControlService(LightsBusiness lightsBusiness) {
		this.lightsBusiness = lightsBusiness;
	}

    /**
     * Changes the color of the light
     *
     * @param node     Device on the network
     * @param newColor New color, as a valid hex String.
     */
    public void changeLight(IotNode node, String newColor) {
        if (newColor.length() != 6) {
            throw new RuntimeException("Not a valid color code.");
        }

        int red = Integer.decode("0x" + newColor.substring(0, 2));
        int green = Integer.decode("0x" + newColor.substring(2, 4));
        int blue = Integer.decode("0x" + newColor.substring(4, 6));


		lightsBusiness.changeColor(new int[]{red, green, blue});
    }

    /**
     * TODO: Sanitize input
     *
	 * @param newMode
	 */
    public void changeMode(String newMode) {
		lightsBusiness.changeMode(newMode);
    }
}
