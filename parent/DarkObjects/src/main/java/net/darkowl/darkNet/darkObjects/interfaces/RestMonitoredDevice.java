/**
 * 
 */
package net.darkowl.darkNet.darkObjects.interfaces;

import net.darkowl.darkNet.darkObjects.xml.config.ConfigurationPropertyKeys;

import org.apache.http.HttpEntity;

/**
 * @author Randy Blancett
 * @since Nov 29, 2015
 * 
 */
public interface RestMonitoredDevice extends Monitorable {
	/**
	 * This is the IP address that the device will check
	 */
	public final static String PROPERTY_IP = ConfigurationPropertyKeys.IP
			.getKey();
	/**
	 * This is a boolean if blank it will be taken as false, or if it is false
	 * or 0;
	 */
	public final static String PROPERTY_SECURE = "HTTPS";

	/**
	 * This will execute a rest get to the endpoint specified
	 * 
	 * @since Nov 29, 2015
	 * @param endpoint
	 *            End point to query
	 * @return Http Entity
	 */
	public HttpEntity getData(String endpoint);

}
