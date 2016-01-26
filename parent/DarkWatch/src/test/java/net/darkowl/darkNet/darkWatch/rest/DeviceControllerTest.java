/**
 * 
 */
package net.darkowl.darkNet.darkWatch.rest;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkWatch.Backer;
import net.darkowl.darkNet.darkWatch.DarkWatch;
import net.darkowl.darkNet.darkWatch.config.RestServerConfig;
import net.darkowl.darkNet.darkWatch.config.WatchConfig;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Randy Blancett
 * @since Jan 16, 2016
 * 
 */
public class DeviceControllerTest {
	private static String baseUrl;

	@BeforeClass
	public static void classSetup() throws IllegalArgumentException,
			IOException {
		WatchConfig.init();
		WatchConfig
				.setProp(WatchConfig.PROPERTY_REST_SERVER_PORT, "8045", true);
		DarkWatch.startRestServer();
		baseUrl = RestServerConfig.getRestURI().toString();
	}

	@AfterClass
	public static void classTearDown() throws IllegalArgumentException,
			IOException {
		WatchConfig.clearProperties();
		DarkWatch.stopRestServer();
	}

	/**
	 * @since Jan 16, 2016
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Backer.clear();
	}

	/**
	 * @since Jan 16, 2016
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Backer.clear();
	}

	@Test
	public void testGet_PlainText() throws ClientProtocolException, IOException {
		RadioThermostat device = new RadioThermostat("Radio", null);
		Backer.getDeviceList().add(device);
		HttpGet get = new HttpGet(baseUrl + "devices");
		get.setHeader("Accept", MediaType.TEXT_PLAIN);
		// get.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		CloseableHttpResponse response = getHttpClient().execute(get);

		assertEquals("name:Radio\nproperties:{}\n",
				IOUtils.toString(response.getEntity().getContent()));

	}

	private static CloseableHttpClient httpclient;

	private static CloseableHttpClient getHttpClient() {
		if (httpclient == null) {
			httpclient = HttpClients.createDefault();
		}
		return httpclient;
	}

	public CloseableHttpResponse getData(String endpoint)
			throws ClientProtocolException, IOException {
		String url = "http";

		url += "://localhost:"
				+ WatchConfig.getString(WatchConfig.PROPERTY_REST_SERVER_PORT)
				+ "/" + endpoint;
		System.out.println("Trying to connect to: " + url);
		return getHttpClient().execute(new HttpGet(url));
	}

}
