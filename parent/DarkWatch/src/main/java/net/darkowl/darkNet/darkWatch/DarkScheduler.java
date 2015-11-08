package net.darkowl.darkNet.darkWatch;

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
		if (DarkScheduler.scheduler == null) {
			DarkScheduler.scheduler = new StdSchedulerFactory().getScheduler();
			DarkScheduler.scheduler.start();
		}
		return DarkScheduler.scheduler;
	}

	public static void schedule(Monitorable device) {
		Scheduler sched;
		try {
			sched = DarkScheduler.getScheduler();
		} catch (final SchedulerException e) {
			DarkScheduler.LOGGER.error("Failed to get Scheduler", e);
			return;
		}

		final JobDetail job = JobBuilder.newJob(device.getThisClass())
				.withIdentity(device.getDeviceName() + "Job", "Monitored")
				.build();

		final Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(device.getDeviceName() + "Trigger", "Monitored")
				.withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
				.build();

		try {
			sched.scheduleJob(job, trigger);
		} catch (final SchedulerException e) {
			DarkScheduler.LOGGER.error("Failed to schedule job", e);
			return;
		}

	}

	public DarkScheduler() throws SchedulerException {
	}

}
