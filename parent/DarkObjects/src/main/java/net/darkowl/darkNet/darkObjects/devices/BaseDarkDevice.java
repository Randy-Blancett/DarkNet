/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.xml.config.Configuration;

/**
 * This is an abstract base Class for all Dark Devices
 * 
 * @author Randy Blancett
 * @since Nov 6, 2015
 * 
 */
public abstract class BaseDarkDevice implements DarkDevice {
	private final String name;
	private final HashMap<String, String> properties;

	public BaseDarkDevice() {
		super();
		this.name = "Unknown";
		this.properties = new HashMap<String, String>();
	}

	/**
	 * Construct the device with a name
	 * 
	 * @since Nov 6, 2015
	 * @param deviceName
	 *            Name that identifies the device
	 * @param configs
	 *            List of configrations to load in to the device
	 */
	public BaseDarkDevice(String deviceName, List<Configuration> configs) {
		this.name = deviceName;
		this.properties = new HashMap<String, String>();
		if (configs != null) {
			for (final Configuration config : configs) {
				if (config.getKey() == null) {
					continue;
				}
				this.properties.put(config.getKey().toUpperCase(),
						config.getValue());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.interfaces.DarkDevice#getDeviceName()
	 */
	@Override
	public String getDeviceName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.interfaces.DarkDevice#getProperties()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getProperties() {
		return (HashMap<String, String>) this.properties.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.interfaces.DarkDevice#getProperty(java
	 * .lang.String)
	 */
	@Override
	public String getProperty(String key) {
		return this.properties.get(key.toUpperCase());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.interfaces.DarkDevice#getPropertyBoolean
	 * (java.lang.String)
	 */
	@Override
	public boolean getPropertyBoolean(String key) {
		final String rawData = this.getProperty(key);
		if (rawData == null || rawData.isEmpty()
				|| rawData.equalsIgnoreCase("false")
				|| rawData.equalsIgnoreCase("0")) {
			return false;
		}
		return true;
	}

	/**
	 * This will be used to reload properties when fired as a Quartz job
	 * 
	 * @since Nov 27, 2015
	 * @param props
	 *            Properties to load in
	 */
	protected void reloadProperties(Map<String, Object> props) {
		for (final Entry<String, Object> prop : props.entrySet()) {
			this.properties.put(prop.getKey(), prop.getValue().toString());
		}
	}

}
