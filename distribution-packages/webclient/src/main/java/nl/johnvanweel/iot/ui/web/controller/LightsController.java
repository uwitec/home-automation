package nl.johnvanweel.iot.ui.web.controller;

import nl.johnvanweel.iot.light.capability.nl.johnvanweel.iot.light.access.cluster.RunMode;
import nl.johnvanweel.iot.service.AvailableDevicesService;
import nl.johnvanweel.iot.service.LightsControlService;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for interacting with a Lights-capable device
 */
@Controller
@RequestMapping("/devices/{device}/Light")
public class LightsController {
    private final AvailableDevicesService devicesService;
    private final LightsControlService lightsControlService;

    @Autowired
    public LightsController(final AvailableDevicesService devicesService, LightsControlService lightsControlService) {
        this.devicesService = devicesService;
        this.lightsControlService = lightsControlService;
    }

    /**
     * Changes the color of the device
     *
     * @param device   device to change
     * @param newColor new color
     */
    @RequestMapping(value = "color/{newColor}", method = RequestMethod.PUT)
    public void changeColor(@PathVariable String device, @PathVariable String newColor) {
        IotNode node = devicesService.getDevice(device);

        lightsControlService.changeLight(node, newColor);

    }

    /**
     * Changes the mode of the device
     *
     * @param device  device to change
     * @param newMode new mode
     */
    @RequestMapping(value = "runmode/{newMode}", method = RequestMethod.PUT)
    public void changeMode(@PathVariable String device, @PathVariable String newMode) {
        IotNode node = devicesService.getDevice(device);

        lightsControlService.changeMode(newMode);
    }

	@RequestMapping(value = "information", method = RequestMethod.GET)
	@ResponseBody
	public RunMode[] getAvailableRunmodes() {
		return RunMode.values();
	}



}
