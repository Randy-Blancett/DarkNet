package net.darkowl.darkNet.darkObjects.devices;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDarkDeviceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		BaseDarkDevice device = new BaseDarkDevice() {
		};

		assertEquals("Unknown", device.getDeviceName());
	}

	@Test
	public void testGetProperties() {
		BaseDarkDevice device = new BaseDarkDevice() {
		};

		assertNotNull(device.getProperties());
	}

	@Test
	public void reloadProperties() {
		BaseDarkDevice device = new BaseDarkDevice() {
		};

		Map<String, Object> props = new HashMap<String, Object>();
		props.put("TEST1", "value1");
		device.reloadProperties(props);
		assertEquals("value1", device.getProperty("test1"));
	}

}
