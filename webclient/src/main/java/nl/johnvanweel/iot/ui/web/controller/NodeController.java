package nl.johnvanweel.iot.ui.web.controller;

import nl.johnvanweel.iot.service.AvailableDevicesService;
import nl.johnvanweel.iot.web.model.IotNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/devices")
public class NodeController {
    private final AvailableDevicesService service;

    @Autowired
    public NodeController(final AvailableDevicesService service) {
        this.service = service;
    }

    @RequestMapping("list")
    @ResponseBody
    public IotNode[] listNodes() {
        return service.getAllDevices();
    }


    @RequestMapping("{name}")
    @ResponseBody
    public IotNode getNodeDetails(@PathVariable String name) {
        return service.getDevice(name);
    }
}

