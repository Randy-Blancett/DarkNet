/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import java.io.IOException;
import java.util.List;

import net.darkowl.darkNet.darkObjects.interfaces.RestMonitoredDevice;
import net.darkowl.darkNet.darkObjects.xml.config.Configuration;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This will provide Rest specific functions for monitoring data
 * 
 * @author Randy Blancett
 * @since Nov 29, 2015
 * 
 */
public abstract class BaseRestDevice extends BaseDarkDevice implements
		RestMonitoredDevice {
	private final static Logger LOGGER = LogManager
			.getLogger(BaseRestDevice.class);

	private static CloseableHttpClient httpclient;

	private static CloseableHttpClient getHttpClient() {
		if (httpclient == null) {
			httpclient = HttpClients.createDefault();
		}
		return httpclient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.interfaces.RestMonitoredDevice#getData
	 * (java.lang.String)
	 */
	@Override
	public HttpEntity getData(String endpoint) {
		String url = "http";
		if (getPropertyBoolean(PROPERTY_SECURE)) {
			url += "s";
		}
		url += "://" + getProperty(PROPERTY_IP) + "/" + endpoint;
		LOGGER.info("Trying to connect to: " + url);
		try {
			CloseableHttpResponse response = getHttpClient().execute(
					new HttpGet(url));
			int responseCode = response.getStatusLine().getStatusCode();
			switch (responseCode) {
			case 200:
				break;
			default:
				LOGGER.error("Failed to get data from: " + url + " received "
						+ responseCode);
				return null;
			}
			return response.getEntity();
		} catch (IOException e) {
			LOGGER.error("Failed to get data from " + url, e);
		}
		return null;
	}

	/**
	 * Create a basic Uknown device
	 * 
	 * @since Nov 29, 2015
	 */
	public BaseRestDevice() {
		super("Unknown", null);
	}

	/**
	 * Create a device and pass it to parent to use
	 * 
	 * @since Nov 29, 2015
	 * @param deviceName
	 *            Device Name
	 * @param configs
	 *            Configurations
	 */
	public BaseRestDevice(String deviceName, List<Configuration> configs) {
		super(deviceName, configs);
	}
}
