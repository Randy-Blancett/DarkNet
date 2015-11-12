/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;

/**
 * This is an abstract base Class for all Dark Devices
 * 
 * @author Randy Blancett
 * @since Nov 6, 2015
 * 
 */
public abstract class BaseDarkDevice implements DarkDevice {
	private final String name;

	/**
	 * Construct the device with a name
	 * 
	 * @since Nov 6, 2015
	 * @param deviceName
	 *            Name that identifies the device
	 */
	public BaseDarkDevice(String deviceName) {
		this.name = deviceName;
	}

	public BaseDarkDevice() {
		super();
		this.name = "Unknown";
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

}
