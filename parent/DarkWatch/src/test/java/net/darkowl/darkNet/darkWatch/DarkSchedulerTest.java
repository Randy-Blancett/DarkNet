/**
 * 
 */
package net.darkowl.darkNet.darkWatch;

import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;

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
	public void testSchedule() throws SchedulerException {
		final DarkDevice dev = new RadioThermostat("Test1");
		DarkScheduler.schedule((Monitorable) dev);

		//
		// Scheduler scheduler = DarkScheduler.getScheduler();
		// for (String groupName : scheduler.getJobGroupNames()) {
		//
		// for (JobKey jobKey :
		// scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
		//
		// String jobName = jobKey.getName();
		// String jobGroup = jobKey.getGroup();
		//
		//
		// System.out.println("[jobName] : " + jobName + " [groupName] : "
		// + jobGroup);
		//
		// }
		//
		// }
	}

}
