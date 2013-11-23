package nl.johnvanweel.iot.ui.web.controller;

import n.johnvanweel.iot.web.model.IotNode;
import nl.johnvanweel.iot.service.IotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/nodes")
public class NodeController {
    private final IotService service;

    @Autowired
    public NodeController(final IotService service) {
        this.service = service;
    }

    @RequestMapping("list")
    @ResponseBody
    public IotNode[] listNodes() {

        return new IotNode[]{new IotNode("test-pi", "IotLight")};
    }
}
