package nl.johnvanweel.iot.sensornetwork;

import nl.johnvanweel.iot.access.cluster.hazelcast.IStorable;

/**
 * User: jvweel
 * Date: 6/9/14
 * Time: 1:18 PM
 */
public class SensorReading implements IStorable {
	private long timestamp;
	private String sensorId;
	private long value;
	private SensorType sensorType;

	public SensorReading(long timestamp, long value, String sensorId, SensorType sensorType) {
		this.timestamp = timestamp;
		this.value = value;
		this.sensorId = sensorId;
		this.sensorType = sensorType;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getKey() {
		return getTimestamp() + getSensorId();
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SensorReading that = (SensorReading) o;

		if (timestamp != that.timestamp) return false;
		if (value != that.value) return false;
		if (sensorId != null ? !sensorId.equals(that.sensorId) : that.sensorId != null) return false;
		if (sensorType != that.sensorType) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (timestamp ^ (timestamp >>> 32));
		result = 31 * result + (sensorId != null ? sensorId.hashCode() : 0);
		result = 31 * result + (int) (value ^ (value >>> 32));
		result = 31 * result + (sensorType != null ? sensorType.hashCode() : 0);
		return result;
	}
}
