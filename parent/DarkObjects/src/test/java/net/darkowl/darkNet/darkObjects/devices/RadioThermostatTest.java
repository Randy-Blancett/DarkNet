/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import static org.junit.Assert.*;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;

import org.junit.Test;

/**
 * @author Randy Blancett
 * @since Oct 28, 2015
 * 
 */
public class RadioThermostatTest {

	@Test
	public void ConstructorTest() {
		assertTrue(new RadioThermostat() instanceof DarkDevice);
	}

}
