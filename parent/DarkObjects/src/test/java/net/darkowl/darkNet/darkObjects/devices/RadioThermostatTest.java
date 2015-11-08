/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import static org.junit.Assert.*;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;

import org.junit.Test;
import org.quartz.Job;

/**
 * @author Randy Blancett
 * @since Oct 28, 2015
 * 
 */
public class RadioThermostatTest {

	@Test
	public void ConstructorTest() {
		RadioThermostat obj = new RadioThermostat("test1");
		assertTrue(obj instanceof DarkDevice);
		assertTrue(obj instanceof Monitorable);
		assertTrue(obj instanceof BaseDarkDevice);
		assertTrue(obj instanceof Job);

		assertEquals("test1", obj.getDeviceName());
	}

}
