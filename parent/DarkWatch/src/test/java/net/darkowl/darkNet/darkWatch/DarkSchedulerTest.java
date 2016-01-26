/**
 * 
 */
package net.darkowl.darkNet.darkWatch;

import java.util.HashMap;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;
import net.darkowl.darkNet.darkObjects.xml.config.ConfigurationPropertyKeys;
import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;

/**
 * @author Randy Blancett
 * @since Nov 6, 2015
 * 
 */
public class DarkSchedulerTest {

	/**
	 * @since Nov 6, 2015
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @since Nov 6, 2015
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchedule() throws SchedulerException, DarkWatchException {
		final DarkDevice dev = new RadioThermostat("Test1", null);
		DarkScheduler.schedule((Monitorable) dev, null);

		DarkScheduler.shutdown();

		Map<String, String> props = new HashMap<String, String>();
		DarkScheduler.schedule((Monitorable) dev, props);

		DarkScheduler.shutdown();

		props.put(ConfigurationPropertyKeys.INTERVAL_SECONDS.getKey(), "40");
		DarkScheduler.schedule((Monitorable) dev, props);

		DarkScheduler.schedule((Monitorable) dev, props);
	}

}
