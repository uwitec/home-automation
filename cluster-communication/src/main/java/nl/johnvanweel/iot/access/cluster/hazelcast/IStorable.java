package nl.johnvanweel.iot.access.cluster.hazelcast;

import java.io.Serializable;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 1:37 PM
 */
public interface IStorable extends Serializable{
	String getKey();
}
