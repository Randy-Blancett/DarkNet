/**
 * 
 */
package net.darkowl.darkNet.darkWatch.rest.service;

import java.util.ArrayList;
import java.util.List;

import net.darkowl.darkNet.darkObjects.devices.BaseDarkDevice;
import net.darkowl.darkNet.darkWatch.Backer;
import net.darkowl.darkNet.darkWatch.rest.interfaces.DevicesServiceInterface;

/**
 * @author Randy Blancett
 * @since Jan 18, 2016
 * 
 */
public class DefaultDeviceService implements DevicesServiceInterface {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkWatch.rest.interfaces.DevicesServiceInterface
	 * #getDevices()
	 */
	@Override
	public List<BaseDarkDevice> getDevices() {
		return new ArrayList<>(Backer.getDeviceList());
	}

}
