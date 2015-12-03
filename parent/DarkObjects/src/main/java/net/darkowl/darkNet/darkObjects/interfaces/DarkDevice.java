/**
 * 
 */
package net.darkowl.darkNet.darkObjects.interfaces;

import java.util.Map;

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

	/**
	 * Get the full list of properties as a Map
	 * 
	 * @since Nov 27, 2015
	 * @return Return a map of the properties
	 */
	Map<String, String> getProperties();

	/**
	 * This will return a string value of a property passed in via the XML
	 * config
	 * 
	 * @since Nov 26, 2015
	 * @param key
	 *            Key value passed in in the XML
	 * @return Return the requested property as a string
	 */
	String getProperty(String key);

	/**
	 * This will convert the string property into a String
	 * 
	 * @since Nov 29, 2015
	 * @param key
	 * @return null,False and 0 = false all others will return true
	 */
	boolean getPropertyBoolean(String key);

}
