package nl.johnvanweel.iot.ui.web.controller;

import nl.johnvanweel.iot.sensornetwork.SensorReading;
import nl.johnvanweel.iot.service.SensorControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for interacting with a Lights-capable device
 */
@Controller
@RequestMapping("/sensor")
public class SensorController {
    private final SensorControlService sensorControlService;

    @Autowired
    public SensorController(final SensorControlService sensorControlService) {
		this.sensorControlService = sensorControlService;
    }

    @RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public SensorReading[] getIlluminationReading() {
     	return sensorControlService.getReadings();
    }
}
