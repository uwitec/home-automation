package nl.johnvanweel.iot.ui.web.controller;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;
import nl.johnvanweel.iot.light.api.RunMode;
import nl.johnvanweel.iot.light.model.LightCapability;
import nl.johnvanweel.iot.light.model.LightRunMode;
import nl.johnvanweel.iot.service.AvailableDevicesService;
import nl.johnvanweel.iot.service.LightsControlService;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for interacting with a Lights-capable device
 */
@Controller
@RequestMapping("/devices/{device}/capability/Light")
public class LightsController {
    private final AvailableDevicesService devicesService;
    private final LightsControlService lightsControlService;

    @Autowired
    public LightsController(final AvailableDevicesService devicesService, LightsControlService lightsControlService) {
        this.devicesService = devicesService;
        this.lightsControlService = lightsControlService;
    }

    /**
     * Changes the mode of the device
     *
     * TODO: Add node
     *
     * @param device  device to change
     * @param newMode new mode
     */
    @RequestMapping(value = "runmode/{newMode}", method = RequestMethod.PUT)
    public void changeMode(@PathVariable String device, @PathVariable String newMode) {
        IotNode node = devicesService.getDevice(device);

        lightsControlService.changeMode(newMode);
    }

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public LightRunMode[] getAvailableRunmodes(@PathVariable String device) {
        IotNode node = devicesService.getDevice(device);

        for (Capability capability : node.getCapabilities()){
            if (capability instanceof LightCapability){
                return ((LightCapability) capability).getLightRunModes();
            }
        }

        return new LightRunMode[0];
	}



}
