package nl.johnvanweel.iot.ui.web.controller;

import nl.johnvanweel.iot.service.AvailableDevicesService;
import nl.johnvanweel.iot.service.LightsControlService;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for interacting with a Lights-capable device
 */
@Controller
@RequestMapping("/devices/{device}")
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
    @RequestMapping(value = "Light/color/{newColor}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "Light/runmode/{newMode}", method = RequestMethod.PUT)
    public void changeMode(@PathVariable String device, @PathVariable String newMode) {
        IotNode node = devicesService.getDevice(device);

        lightsControlService.changeMode(node, newMode);
    }

}
