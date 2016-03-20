package nl.johnvanweel.iot.service;

import nl.johnvanweel.iot.access.cluster.business.ClusterBusiness;
import nl.johnvanweel.iot.access.cluster.listener.DefaultEntryListener;
import nl.johnvanweel.iot.access.cluster.message.NodeIdentification;
import nl.johnvanweel.iot.web.model.IotNode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class AvailableDevicesService implements DefaultEntryListener<Object,Object> {
    private Logger logger = Logger.getLogger(AvailableDevicesService.class);

	private final ClusterBusiness clusterBusiness;

    @Autowired
    public AvailableDevicesService(ClusterBusiness clusterBusiness) {
		this.clusterBusiness = clusterBusiness;
	}


    public IotNode[] getAllDevices() {
		List<IotNode> results = new ArrayList<>();
        for (NodeIdentification node : clusterBusiness.getAllDevices().values()){
			results.add(new IotNode(node.getUuid().toString(), node.getCapabilities()));
		}

		return results.toArray(new IotNode[results.size()]);
    }

    public IotNode getDevice(String name) {
        return new IotNode(name, clusterBusiness.getAllDevices().get(name).getCapabilities());
    }
}
