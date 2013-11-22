package nl.johnvanweel.iot.music.model.messaging;

import org.jgroups.Address;

import java.io.Serializable;

/**
 * User: John van Weel
 * Date: 1/10/12
 * Time: 6:48 PM
 */
public abstract class GroupMessage implements Serializable {
    private Address sender;

    public Address getSender() {
        return sender;
    }

    public void setSender(Address sender) {
        this.sender = sender;
    }
}
