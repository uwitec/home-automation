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
     * TODO: Sanitize input
     *
	 * @param newMode
	 */
    public void changeMode(String newMode) {
		lightsBusiness.changeMode(newMode);
    }
}
