package nl.johnvanweel.iot.access.cluster;

import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import nl.johnvanweel.iot.access.cluster.message.GroupMessage;
import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.ReceiverAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

/**
 * Created by john on 11/17/13.
 */
public class JGroupsChannel implements IChannel {
    private Resource configuration;
    private String clusterName;
    private JChannel channel;

    @Autowired
    private ReceiverAdapter receiverAdapter;

    //    @Autowired
    public JGroupsChannel() {
//        this.receiverAdapter = receiverAdapter;
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        channel = new JChannel(configuration.getFilename());
        channel.setReceiver(receiverAdapter);
        channel.connect(clusterName);
    }

    @Override
    public void broadcast(GroupMessage message) {
        try {
            channel.send(null, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTo(Address to, GroupMessage message) {
        try {
            channel.send(to, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setConfiguration(Resource configuration) {
        this.configuration = configuration;
    }

    public Resource getConfiguration() {
        return configuration;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public JChannel getChannel() {
        return channel;
    }

    public void setReceiverAdapter(ReceiverAdapter receiverAdapter) {
        this.receiverAdapter = receiverAdapter;
    }

}
