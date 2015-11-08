/**
 * 
 */
package net.darkowl.darkNet.darkObjects.interfaces;

import org.quartz.Job;


/**
 * This is the base interface that all Device Objects in the DarkNet must
 * Implement
 * 
 * @author Randy Blancett
 * @since Oct 28, 2015
 * 
 */
public interface DarkDevice {
	/**
	 * Return the name that identifies the device
	 * 
	 * @since Nov 6, 2015
	 * @return The device name
	 */
	String getDeviceName();


}
