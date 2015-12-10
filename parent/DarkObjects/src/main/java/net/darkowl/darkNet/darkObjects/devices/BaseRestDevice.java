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
	private static CloseableHttpClient httpclient;

	private final static Logger LOGGER = LogManager
			.getLogger(BaseRestDevice.class);

	private static CloseableHttpClient getHttpClient() {
		if (BaseRestDevice.httpclient == null) {
			BaseRestDevice.httpclient = HttpClients.createDefault();
		}
		return BaseRestDevice.httpclient;
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
		if (this.getPropertyBoolean(RestMonitoredDevice.PROPERTY_SECURE)) {
			url += "s";
		}
		url += "://" + this.getProperty(RestMonitoredDevice.PROPERTY_IP) + "/"
				+ endpoint;
		BaseRestDevice.LOGGER.info("Trying to connect to: " + url);
		try {
			final CloseableHttpResponse response = BaseRestDevice
					.getHttpClient().execute(new HttpGet(url));
			final int responseCode = response.getStatusLine().getStatusCode();
			switch (responseCode) {
			case 200:
				break;
			default:
				BaseRestDevice.LOGGER.error("Failed to get data from: " + url
						+ " received " + responseCode);
				return null;
			}
			return response.getEntity();
		} catch (final IOException e) {
			BaseRestDevice.LOGGER.error("Failed to get data from " + url, e);
		}
		return null;
	}
}
