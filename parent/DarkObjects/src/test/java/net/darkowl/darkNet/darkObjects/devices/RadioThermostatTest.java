/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;

import org.junit.Assert;
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
		RadioThermostat obj = new RadioThermostat();
		Assert.assertTrue(obj instanceof DarkDevice);
		Assert.assertTrue(obj instanceof Monitorable);
		Assert.assertTrue(obj instanceof BaseDarkDevice);
		Assert.assertTrue(obj instanceof Job);
		Assert.assertEquals("Unknown", obj.getDeviceName());

		obj = new RadioThermostat("test1");
		Assert.assertTrue(obj instanceof DarkDevice);
		Assert.assertTrue(obj instanceof Monitorable);
		Assert.assertTrue(obj instanceof BaseDarkDevice);
		Assert.assertTrue(obj instanceof Job);

		Assert.assertEquals("test1", obj.getDeviceName());
	}

}
