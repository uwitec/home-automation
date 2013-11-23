package nl.johnvanweel.iot.capability;

import nl.johnvanweel.iot.access.cluster.capabilities.Capability;

/**
 * Device is cluster aware and will listen for identification responses.
 */
public class ClusterAwareCapability extends Capability {
    public ClusterAwareCapability() {
        super("ClusterAware", "Can become aware of the cluster.");
    }
}
