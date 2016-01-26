package net.darkowl.darkNet.darkWatch.rest.service;

import static org.junit.Assert.*;
import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkWatch.Backer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultDeviceServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDevices() {
		RadioThermostat device = new RadioThermostat();
		DefaultDeviceService service = new DefaultDeviceService();

		Backer.getDeviceList().add(device);
		assertEquals(1, service.getDevices().size());
	}

}
