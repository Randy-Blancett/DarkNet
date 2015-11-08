package net.darkowl.darkNet.darkWatch;

import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class DarkScheduler {
	private final static Logger LOGGER = LogManager
			.getLogger(DarkScheduler.class);
	private static Scheduler scheduler;

	protected synchronized static Scheduler getScheduler()
			throws SchedulerException {
		if (scheduler == null) {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		}
		return scheduler;
	}

	public DarkScheduler() throws SchedulerException {
	}

	public static void schedule(Monitorable device) {
		Scheduler sched;
		try {
			sched = getScheduler();
		} catch (SchedulerException e) {
			LOGGER.error("Failed to get Scheduler", e);
			return;
		}

		JobDetail job = JobBuilder.newJob(device.getThisClass())
				.withIdentity(device.getDeviceName() + "Job", "Monitored")
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(device.getDeviceName() + "Trigger", "Monitored")
				.withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
				.build();

		try {
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("Failed to schedule job", e);
			return;
		}

	}

}
