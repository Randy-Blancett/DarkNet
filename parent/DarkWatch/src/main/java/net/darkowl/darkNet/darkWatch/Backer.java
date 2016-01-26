/**
 * 
 */
package net.darkowl.darkNet.darkWatch;

import java.util.ArrayList;
import java.util.List;

import net.darkowl.darkNet.darkObjects.devices.BaseDarkDevice;

/**
 * @author Randy Blancett
 * @since Jan 18, 2016
 * 
 */
public class Backer {

	private static List<BaseDarkDevice> deviceList = null;

	public static void clear() {
		Backer.deviceList = null;
	}

	/**
	 * Get the list of devices that have been loaded
	 * 
	 * @since Jan 18, 2016
	 * @return
	 */
	public static List<BaseDarkDevice> getDeviceList() {
		if (Backer.deviceList == null) {
			Backer.deviceList = new ArrayList<BaseDarkDevice>();
		}
		return Backer.deviceList;
	}
}
