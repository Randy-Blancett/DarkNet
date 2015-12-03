/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;
import net.darkowl.darkNet.darkObjects.interfaces.RestMonitoredDevice;
import net.darkowl.darkNet.darkObjects.test.restEndpoints.RadioThermostat_tstat;
import net.darkowl.darkNet.darkObjects.test.restEndpoints.RadioThermostat_tstat.ResponseType;
import net.darkowl.darkNet.darkObjects.xml.config.Configuration;

import org.apache.http.ExceptionLogger;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quartz.Job;

/**
 * @author Randy Blancett
 * @since Oct 28, 2015
 * 
 */
public class RadioThermostatTest {

	private static HttpServer server = null;

	@BeforeClass
	public static void setUp() throws IOException {
		RadioThermostatTest.server = ServerBootstrap.bootstrap()
				.setListenerPort(8080).setServerInfo("Test/1.1")
				.setExceptionLogger(ExceptionLogger.STD_ERR)
				.registerHandler("/tstat", new RadioThermostat_tstat())
				.create();
		RadioThermostatTest.server.start();
	}

	@AfterClass
	public static void tearDown() {
		RadioThermostatTest.server.shutdown(5, TimeUnit.SECONDS);
	}

	@Test
	public void constructorTest() {
		RadioThermostat obj = new RadioThermostat();
		Assert.assertTrue(obj instanceof DarkDevice);
		Assert.assertTrue(obj instanceof Monitorable);
		Assert.assertTrue(obj instanceof RestMonitoredDevice);
		Assert.assertTrue(obj instanceof BaseDarkDevice);
		Assert.assertTrue(obj instanceof Job);
		Assert.assertEquals("Unknown", obj.getDeviceName());

		obj = new RadioThermostat("test1", null);
		Assert.assertTrue(obj instanceof DarkDevice);
		Assert.assertTrue(obj instanceof Monitorable);
		Assert.assertTrue(obj instanceof RestMonitoredDevice);
		Assert.assertTrue(obj instanceof BaseDarkDevice);
		Assert.assertTrue(obj instanceof Job);

		Assert.assertEquals("test1", obj.getDeviceName());
		Assert.assertNull(obj.getProperty("test1"));

		// Pass in Configuration properties
		final List<Configuration> configs = new ArrayList<Configuration>();
		Configuration config = new Configuration();
		config.setKey("Test1");
		config.setValue("Value1");
		configs.add(config);
		config = new Configuration();
		config.setKey("Test2");
		config.setValue("Value2");
		configs.add(config);
		config = new Configuration();
		config.setValue("Value3");
		configs.add(config);

		obj = new RadioThermostat("test2", configs);
		Assert.assertTrue(obj instanceof DarkDevice);
		Assert.assertTrue(obj instanceof Monitorable);
		Assert.assertTrue(obj instanceof RestMonitoredDevice);
		Assert.assertTrue(obj instanceof BaseDarkDevice);
		Assert.assertTrue(obj instanceof Job);

		Assert.assertEquals("test2", obj.getDeviceName());
		Assert.assertEquals("Value1", obj.getProperty("Test1"));
		Assert.assertEquals("Value2", obj.getProperty("Test2"));
	}

	@Test
	public void testGetDeviceInfo() {
		final List<Configuration> configs = new ArrayList<Configuration>();
		final Configuration config = new Configuration();
		config.setKey(RestMonitoredDevice.PROPERTY_IP);
		config.setValue("127.0.0.1:8080");
		configs.add(config);

		final RadioThermostat obj = new RadioThermostat("test2", configs);

		RadioThermostat_tstat.setType(ResponseType.TYPE_404);
		obj.getDeviceInfo();
		RadioThermostat_tstat.setType(ResponseType.TYPE_200_GOOD_DATA);
		obj.getDeviceInfo();
	}

	@Test
	public void testGetPropertyBoolean() {
		final List<Configuration> configs = new ArrayList<Configuration>();
		Configuration config = new Configuration();
		config.setKey("false1");
		config.setValue(null);
		configs.add(config);
		config = new Configuration();
		config.setKey("false2");
		config.setValue("");
		configs.add(config);
		config = new Configuration();
		config.setKey("false3");
		config.setValue("FALSE");
		configs.add(config);
		config = new Configuration();
		config.setKey("false4");
		config.setValue("FaLSe");
		configs.add(config);
		config = new Configuration();
		config.setKey("false5");
		config.setValue("0");
		configs.add(config);
		config = new Configuration();
		config.setKey("true1");
		config.setValue("1");
		configs.add(config);
		config = new Configuration();
		config.setKey("true2");
		config.setValue("TRUE");
		configs.add(config);
		config = new Configuration();
		config.setKey("true3");
		config.setValue("DOG");
		configs.add(config);

		final RadioThermostat obj = new RadioThermostat("test2", configs);

		Assert.assertEquals(false, obj.getPropertyBoolean("false1"));
		Assert.assertEquals(false, obj.getPropertyBoolean("false2"));
		Assert.assertEquals(false, obj.getPropertyBoolean("false3"));
		Assert.assertEquals(false, obj.getPropertyBoolean("false4"));
		Assert.assertEquals(false, obj.getPropertyBoolean("false5"));
		Assert.assertEquals(true, obj.getPropertyBoolean("true1"));
		Assert.assertEquals(true, obj.getPropertyBoolean("true2"));
		Assert.assertEquals(true, obj.getPropertyBoolean("true3"));
	}

}
